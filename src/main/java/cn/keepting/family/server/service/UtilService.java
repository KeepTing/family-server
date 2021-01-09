package cn.keepting.family.server.service;

import cn.keepting.family.server.api.model.WeatherInfo;
import cn.keepting.family.server.controller.dto.CalenderDTO;
import cn.keepting.family.server.controller.dto.WeatherDTO;
import cn.keepting.family.server.dao.CalenderDao;
import cn.keepting.family.server.dao.CityDao;
import cn.keepting.family.server.dao.model.CalenderPo;
import cn.keepting.family.server.dao.model.CityPo;
import cn.keepting.family.server.repository.WeatherRepository;
import cn.keepting.family.server.task.WeatherTask;
import cn.keepting.family.server.util.GPSConvertUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.service
 * @date:2021/1/7
 **/
@Slf4j
@Service
public class UtilService {

    @Resource
    CalenderDao calenderDao;

    @Resource
    CityDao cityDao;

    @Autowired
    WeatherRepository weatherRepository;

    //获取本月的日期
    public List<CalenderDTO> getCalenderByMonth(String month) {
        List<CalenderPo> list = calenderDao.selectList(new EntityWrapper<CalenderPo>()
                .eq("month", month));
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        return list.stream().map(CalenderDTO::copyFromPo)
                .collect(Collectors.toList());
    }

    //获取当天的日历
    public CalenderDTO getCalenderByDay(String date) {

        String[] str = StringUtils.split(date, "-");

        List<CalenderPo> list = calenderDao.selectList(new EntityWrapper<CalenderPo>()
                .eq("year", str[0])
                .eq("month", str[1])
                .eq("day", str[2]));
        if (CollectionUtils.isEmpty(list)) {
            return CalenderDTO.builder().build();
        }

        return CalenderDTO.copyFromPo(list.get(0));
    }

    //今日天气
    public WeatherDTO todayWeather(Double lng, Double lat, String cityId) {
        if (Objects.isNull(cityId)) {
            CityPo localCity = localCity(lng, lat);
            if (Objects.isNull(localCity)) {
                log.info("未获取到定位城市：{},{}", lng, lat);
                return null;
            }
            cityId = localCity.getId();
        }

        WeatherInfo info = weatherRepository.getTodayWeather(cityId);
        if (Objects.isNull(info)) {
            return null;
        }

        WeatherDTO weather = WeatherDTO.copyFromPo(info);
        return weather;
    }

    //定位城市
    private CityPo localCity(Double lng, Double lat) {
        if (Objects.isNull(lng) || Objects.isNull(lat)) {
//            return cityList.get(0);
            return null;
        }
        List<CityPo> cityList = WeatherTask.allCity;
        if (CollectionUtils.isEmpty(cityList)) {
            cityList = cityDao.selectList(null);
        }
        if (CollectionUtils.isEmpty(cityList)) {
            return null;
        }

        double maxDis = 10000000;
        CityPo localCity = cityList.get(0);
        for (CityPo city : cityList) {
            if (Objects.nonNull(city.getLon()) && Objects.nonNull(city.getLat())) {
                double dis = GPSConvertUtil.distance(lng, lat, Double.parseDouble(city.getLon()), Double.parseDouble(city.getLat()));

                if (dis < maxDis) {
                    localCity = city;
                    maxDis = dis;
                }
            }
        }

        return localCity;

    }
}

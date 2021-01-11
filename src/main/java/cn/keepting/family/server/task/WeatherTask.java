package cn.keepting.family.server.task;

import cn.keepting.family.server.api.WeatherApi;
import cn.keepting.family.server.api.model.HefengWeatherInfo;
import cn.keepting.family.server.api.model.WeatherInfo;
import cn.keepting.family.server.dao.CityDao;
import cn.keepting.family.server.dao.HefengCityDao;
import cn.keepting.family.server.dao.model.CityPo;
import cn.keepting.family.server.dao.model.HefengCityPo;
import cn.keepting.family.server.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.task
 * @date:2021/1/9
 **/
@Slf4j
@Service
public class WeatherTask {

    @Resource
    HefengCityDao hefengCityDao;

    @Autowired
    WeatherApi weatherApi;

    @Autowired
    WeatherRepository weatherRepository;

    public static List<HefengCityPo> allCity = new ArrayList<>();

    //    @Scheduled(fixedRate = 3 * 60 * 60 * 1000)
    public void todayWeather() {
        List<HefengCityPo> cityList = allCity;
        if (CollectionUtils.isEmpty(cityList)) {
            cityList = hefengCityDao.selectList(null);
        }
        if (CollectionUtils.isEmpty(cityList)) {
            return;
        }

        cityList.forEach(city -> {
            HefengWeatherInfo weatherInfo = weatherApi.getHefengWeather(city.getId());
            if (Objects.nonNull(weatherInfo)) {
                weatherRepository.saveTodayWeather(city.getId()+"", weatherInfo);
            }
        });
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    public void allCity() {
        List<HefengCityPo> cityList = hefengCityDao.selectList(null);
        if (CollectionUtils.isEmpty(cityList)) {
            return;
        }

        allCity = cityList;
    }
}

package cn.keepting.family.server.service;

import cn.keepting.family.server.controller.dto.CalenderDTO;
import cn.keepting.family.server.dao.CalenderDao;
import cn.keepting.family.server.dao.model.CalenderPo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
}

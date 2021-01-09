package cn.keepting.family.server.controller;

import cn.keepting.family.server.constant.BaseResponse;
import cn.keepting.family.server.controller.dto.WeatherDTO;
import cn.keepting.family.server.controller.model.CalenderDayResp;
import cn.keepting.family.server.controller.model.CalenderMonthResp;
import cn.keepting.family.server.controller.model.CalenderReq;
import cn.keepting.family.server.service.UtilService;
import cn.keepting.family.server.task.WeatherTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.controller
 * @date:2021/1/7
 **/
@Api(tags = "工具")
@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    UtilService utilService;
    @Autowired
    WeatherTask weatherTask;

    @ApiOperation("")
    @GetMapping("/calender/month")
    public BaseResponse<CalenderMonthResp> calender(String month) {
        return BaseResponse.ok(CalenderMonthResp.builder()
                .cals(utilService.getCalenderByMonth(month))
                .build());
    }

    @ApiOperation("")
    @GetMapping("/calender/monthGroupByWeek")
    public BaseResponse<CalenderMonthResp> monthGroupByWeek(String month) {
        return BaseResponse.ok(CalenderMonthResp.builder()
                .cals(utilService.getCalenderByMonth(month))
                .build());
    }

    @ApiOperation("")
    @GetMapping("/calender/day")
    public BaseResponse<CalenderDayResp> calenderDay(String date) {
        return BaseResponse.ok(CalenderDayResp.builder()
                .cal(utilService.getCalenderByDay(date))
                .build());
    }

    @ApiOperation("")
    @GetMapping("/weather/today")
    public BaseResponse<WeatherDTO> todayWeather(Double lng, Double lat, String cityId) {
        return BaseResponse.ok(utilService.todayWeather(lng, lat, cityId));
    }

    @ApiOperation("")
    @GetMapping("/weather/upload")
    public BaseResponse uploadWeather() {
        weatherTask.todayWeather();
        return BaseResponse.ok();
    }
}

package cn.keepting.family.server.controller.dto;

import cn.keepting.family.server.api.model.WeatherAlerm;
import cn.keepting.family.server.api.model.WeatherInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.controller.dto
 * @date:2021/1/9
 **/
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDTO {

    @ApiModelProperty("城市id")
    private String cityid;
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("周几")
    private String weekend;
    @ApiModelProperty("城市名")
    private String city;
    @ApiModelProperty("国家")
    private String country;
    @ApiModelProperty("天气")
    private String weather;
    @ApiModelProperty("图片")
    private String weaImg;
    @ApiModelProperty("实时温度")
    private String realTem;
    @ApiModelProperty("最高温")
    private String highTem;
    @ApiModelProperty("最低温")
    private String lowTem;
    @ApiModelProperty("风向")
    private String wind;
    @ApiModelProperty("风速")
    private String windSpeed;
    @ApiModelProperty("风力等级")
    private String windLevel;
    @ApiModelProperty("湿度")
    private String humidity;
    @ApiModelProperty("能见度")
    private String visibility;
    @ApiModelProperty("气压")
    private String pressure;
    @ApiModelProperty("空气质量")
    private String air;
    @ApiModelProperty("pm2.5指数")
    private String airPm25;
    @ApiModelProperty("空气等级")
    private String airLevel;
    @ApiModelProperty("空气提示")
    private String airTips;
    @ApiModelProperty("预警类型")
    private String alarmType;
    @ApiModelProperty("预警等级")
    private String alarmLevel;
    @ApiModelProperty("预警描述")
    private String alarmContent;

    public static WeatherDTO copyFromPo(WeatherInfo info) {
        return WeatherDTO.builder()
                .date(info.getDate())
                .city(info.getCity())
                .cityid(info.getCityid())
                .country(info.getCountry())
                .air(info.getAir())
                .airPm25(info.getAir_pm25())
                .airLevel(info.getAir_level())
                .airTips(info.getAir_tips())
                .wind(info.getWin())
                .windLevel(info.getWin_speed())
                .realTem(info.getTem())
                .highTem(info.getTem1())
                .lowTem(info.getTem2())
                .weather(info.getWea())
                .weaImg(info.getWea_img())
                .weekend(info.getWeek())
                .humidity(info.getHumidity())
                .pressure(info.getPressure())
                .visibility(info.getVisibility())
                .build();
    }
}

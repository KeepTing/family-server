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
    private String cityId;
    @ApiModelProperty("城市名")
    private String city;
    @ApiModelProperty("天气")
    private String weather;
    @ApiModelProperty("图片")
    private String icon;
    @ApiModelProperty("实时温度")
    private String temp;

//    public static WeatherDTO copyFromPo(WeatherInfo info) {
//        return WeatherDTO.builder()
//                .date(info.getDate())
//                .city(info.getCity())
//                .cityid(info.getCityid())
//                .country(info.getCountry())
//                .air(info.getAir())
//                .airPm25(info.getAir_pm25())
//                .airLevel(info.getAir_level())
//                .airTips(info.getAir_tips())
//                .wind(info.getWin())
//                .windLevel(info.getWin_speed())
//                .realTem(info.getTem())
//                .highTem(info.getTem1())
//                .lowTem(info.getTem2())
//                .weather(info.getWea())
//                .weaImg(info.getWea_img())
//                .weekend(info.getWeek())
//                .humidity(info.getHumidity())
//                .pressure(info.getPressure())
//                .visibility(info.getVisibility())
//                .build();
//    }
}

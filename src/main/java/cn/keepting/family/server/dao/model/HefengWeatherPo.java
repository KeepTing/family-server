package cn.keepting.family.server.dao.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao.model
 * @date:2021/1/11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("hefeng_weather")
public class HefengWeatherPo {
    /**
     * id
     */
    private Long id;

    /**
     * update_time
     */
    private String updateTime;

    /**
     * 实况温度，默认单位：摄氏度
     */
    private String temp;

    /**
     * 实况体感温度，默认单位：摄氏度
     */
    private String feelsLike;

    /**
     * 当前天气状况和图标的代码
     */
    private String icon;

    /**
     * 实况天气状况的文字描述
     */
    private String text;

    /**
     * 实况风向
     */
    private String windDir;

    /**
     * 实况风力等级
     */
    private String windScale;

    /**
     * 实况风速，公里/小时
     */
    private String windSpeed;

    /**
     * 实况相对湿度，百分比数值
     */
    private String humidity;

    /**
     * 实况降水量，默认单位：毫米
     */
    private String precip;

    /**
     * 实况大气压强
     */
    private String pressure;

    /**
     * 实况能见度，默认单位：公里
     */
    private String vis;

    /**
     * cloud
     */
    private String cloud;

    /**
     * dew
     */
    private String dew;
}

package cn.keepting.family.server.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.api.model
 * @date:2021/1/8
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherAlerm {
    private String alarm_type;
    private String alarm_level;
    private String alarm_content;
}

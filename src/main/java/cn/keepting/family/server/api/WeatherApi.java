package cn.keepting.family.server.api;

import cn.keepting.family.server.api.model.HefengWeatherInfo;
import cn.keepting.family.server.api.model.WeatherInfo;
import cn.keepting.family.server.config.RestUrl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.api
 * @date:2021/1/8
 **/
@Slf4j
@Service
public class WeatherApi {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestUrl restUrl;

    public HefengWeatherInfo getHefengWeather(Integer cityId) {
        String res = restTemplate.getForObject(restUrl.getHefengWeatherUrl(), String.class, cityId);
        if (StringUtils.isBlank(res)) {
            return null;
        }

        JSONObject json = JSONObject.parseObject(res);
        if (Objects.isNull(json) || Objects.isNull(json.getJSONObject("now"))) {
            return null;
        }

        return JSON.parseObject(json.getJSONObject("now").toJSONString(), HefengWeatherInfo.class);
    }

    public WeatherInfo getWeather(String cityId) {
        String res = restTemplate.getForObject(restUrl.getWeatherUrl(), String.class, cityId);
        if (StringUtils.isBlank(res)) {
            return null;
        }

        return JSON.parseObject(res, WeatherInfo.class);
    }
}

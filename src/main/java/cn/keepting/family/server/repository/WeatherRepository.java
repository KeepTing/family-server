package cn.keepting.family.server.repository;

import cn.keepting.family.server.api.model.WeatherInfo;
import cn.keepting.family.server.repository.model.RedisKeys;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: create by fuhao.xu
 * @description: com.chealile.inertcity.repository
 * @date:2020/10/28
 **/
@Repository
public class WeatherRepository {

    @Autowired
    StringRedisTemplate redisTemplate;


    public void saveTodayWeather(String cityId, WeatherInfo info) {
        redisTemplate.opsForValue().set(RedisKeys.cityWeatherKey(cityId), JSON.toJSONString(info));
    }

    public WeatherInfo getTodayWeather(String cityId) {
        String res = redisTemplate.opsForValue().get(RedisKeys.cityWeatherKey(cityId));
        if (StringUtils.isBlank(res)) {
            return null;
        }
        return JSON.parseObject(res, WeatherInfo.class);
    }

}

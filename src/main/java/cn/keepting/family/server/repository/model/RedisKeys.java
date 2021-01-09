package cn.keepting.family.server.repository.model;

/**
 * @author: create by fuhao.xu
 * @description: com.yg84.mp.coupon.repository.model
 * @date:2020/6/18
 **/
public class RedisKeys {

    private static String PREFIX = "family:server:";


    public static String cityWeatherKey(String cityId) {
        return PREFIX + "weather:today:" + cityId;
    }
}

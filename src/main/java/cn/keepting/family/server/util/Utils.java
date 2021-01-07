package cn.keepting.family.server.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author: create by fuhao.xu
 * @description: com.yg84.mp.coupon.util
 * @date:2020/7/30
 **/
public class Utils {

    public static double getDouble(double price) {
        return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String hidePhone(String phone) {
        return StringUtils.substring(phone, 0, 3) + "****" + StringUtils.substring(phone, 7, phone.length());
    }
}

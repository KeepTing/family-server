package cn.keepting.family.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: create by fuhao.xu
 * @description: com.yg84.mp.coupon.config
 * @date:2020/6/18
 **/
@Data
@Component
@ConfigurationProperties(prefix = "yg.rest-url")
public class RestUrl {
    String sendMsgUrl;
    String poiSearchUrl;
    String weatherUrl;

    String baiduPoiUrl;
}

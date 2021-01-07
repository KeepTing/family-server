package cn.keepting.family.server.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by MI on 2019/7/9.
 */
public class UuidUtils {

    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", ""); //全球唯一编号hash值
    }

    private static String createOrderId(){
        String pre = "carpooling";

        pre += DateUtil.nowForStr("yyyyMMddHHmmssSSS");
        String suffix = getId().substring(0,5);

        return pre + suffix;
    }

    public static String getOrderId(){
        while (true){
            String uuid32 = createOrderId();
            if( map.containsKey(uuid32) ) {
                continue;
            }
            map.put(uuid32, "");
            return uuid32;
        }
    }

}

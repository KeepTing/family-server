package cn.keepting.family.server.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author xiaoyu
 */
public final class SignUtils {

    public static final String SIGN_NAME = "sign";

    private SignUtils() {

    }

    public static String createSignStr(Map<String, String> data, String signKey) {
        // 生成签名前先去除sign
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SIGN_NAME)) {
                continue;
            }
            if (data != null && data.get(k) != null && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(signKey);
        return sb.toString();
    }

    public static String getSignStrWithUrlEncode(Map<String, String> data, String signKey) {
        // 生成签名前先去除sign
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SIGN_NAME)) {
                continue;
            }
            if (data != null && data.get(k) != null && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(signKey);
        return sb.toString();
    }

    public static String md5(String str) {
        return HashKit.md5(str).toUpperCase();
    }


    public static boolean checkSign(Map<String, String> params, String signKey) {
        String sign = params.get("sign");
        String localSign = md5(SignUtils.createSignStr(params, signKey));
        return sign.equals(localSign);
    }


    public static String getGig(Map<String, Object> params, String privateKey) {
        StringBuilder sBuilder = new StringBuilder();

        List<String> paramNames = getSortedParameterNames(params);
        for (String name : paramNames) {
            sBuilder.append(name);
            sBuilder.append(params.get(name));
        }

        sBuilder.append(privateKey);

        String plainSign = sBuilder.toString();
        System.out.println("plain sig:" + plainSign);
        String sig = md5(plainSign);

        return sig;
    }

    private static List<String> getSortedParameterNames(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>();
        for (String key : params.keySet()) {
            if (StringUtils.equalsIgnoreCase("sig", key)) {
                continue;
            }
            keys.add(key);
        }
        Collections.sort(keys);
        return keys;
    }


}

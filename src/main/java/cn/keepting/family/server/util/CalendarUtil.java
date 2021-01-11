package cn.keepting.family.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.util
 * @date:2021/1/7
 **/
public class CalendarUtil {
    /**
     * 聚合数据 key
     */
    private static final String KEY = "e5af5062813363cf63a36852394b0f87";
    /**
     * 万年历接口 某天详细 URL
     */
    private static final String CALENDAR_DAY_URL = "http://v.juhe.cn/calendar/day";
    /**
     * 万年历接口 近期假期 URL
     */
    private static final String NEAR_HOLIDAY_URL = "http://v.juhe.cn/calendar/month";

    /**
     * 判断某天是否是 节假日 休息日
     *
     * @param date 日期
     * @return boolean 是 true 否 false
     * @author ChenXueSong
     * @datetime 2020/7/14 11:24
     */
    public static boolean isHolidayAndRestDay(Date date) {
        // 调用查询近期节假日
        NearHoliday nearHoliday = findNearHoliday(date);
        if (nearHoliday == null) {
            return true;
        }
        String param = DateUtil.format(date, "yyyy-M-d");
        // 节假日数组 不为 null 数组长度 大于 0
        if (nearHoliday.getHolidayArray() != null && nearHoliday.getHolidayArray().size() > 0) {
            // 循环遍历 近期假期
            for (Holiday holiday : nearHoliday.getHolidayArray()) {
                // 循环遍历 假期中的 日期，判断是 假期 还是 上班
                for (HolidayDay holidayDay : holiday.getList()) {
                    if (param.equals(holidayDay.getDate())) {
                        // 日期状态 为 1 是假期日，为 2 是 上班日
                        return holidayDay.getStatus().compareTo(1) == 0;
                    }
                }
            }
        }
        // 一周天
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // 休息日
        String[] restDays = new String[]{"星期六", "星期日"};
        // 设置日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (String day : restDays) {
            // 比较今天 是否是 休息日
            if (day.equals(weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 调用日历接口查询 某天详细信息
     *
     * @param date 日期
     * @return com.centling.snake.ufan.personal.juhe.CalendarUtil.CalendarDate
     * @author ChenXueSong
     * @datetime 2020/7/14 12:03
     */
    public static CalendarDate findCalendarDate(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        String param = DateUtil.format(date, "yyyy-M-d");
        String result = restTemplate.getForObject(CALENDAR_DAY_URL + "?date=" + param + "&key=" + KEY, String.class);
        // 获取日历 Data
        JSONObject data = resolvingResult(result);
        if (data == null) {
            return null;
        }
        return JSON.toJavaObject(data, CalendarDate.class);
    }

    /**
     * 调用日历接口查询 近期假期
     *
     * @param date 某月日期
     * @return com.centling.snake.ufan.personal.juhe.CalendarUtil.NearHoliday
     * @author ChenXueSong
     * @datetime 2020/7/14 14:46
     */
    public static NearHoliday findNearHoliday(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        String param = DateUtil.format(date, "yyyy-M");
        String result = restTemplate.getForObject(NEAR_HOLIDAY_URL + "?year-month=" + param + "&key=" + KEY, String.class);
        // 获取日历 Data
        JSONObject data = resolvingResult(result);
        if (data == null) {
            return null;
        }
        if (data.getInteger("error_code").compareTo(217701) == 0) {
            // error_code 为 217701，无对应参数的数据返回，无假期，返回空内容对象
            return new NearHoliday();
        }
        return JSON.toJavaObject(data, NearHoliday.class);
    }

    /**
     * 解析聚合数据接口返回 json 字符串 转换为 jsonObject
     *
     * @param result json 字符串
     * @return com.alibaba.fastjson.JSONObject
     * @author ChenXueSong
     * @datetime 2020/7/14 15:54
     */
    public static JSONObject resolvingResult(String result) {
        if (StringUtils.isBlank(result)) {
            // 接口返回异常
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(result);
        // 接口状态码，返回异常
        Integer errorCode = jsonObject.getInteger("error_code");
        if (errorCode.compareTo(0) != 0 && errorCode.compareTo(217701) != 0) {
            return null;
        }
        if (errorCode.compareTo(217701) == 0) {
            // error_code 为 217701，无对应参数的数据返回，无假期,返回此 json
            return jsonObject;
        }
        return jsonObject.getJSONObject("result").getJSONObject("data");
    }

    /**
     * 日历信息-内部类
     */
    @Data
    private static class CalendarDate {
        /**
         * 假日
         */
        private String holiday;
        /**
         * 属相
         */
        private String animalsYear;
        /**
         * 忌
         */
        private String avoid;
        /**
         * 假日描述
         */
        private String desc;
        /**
         * 周几
         */
        private String weekday;
        /**
         * 宜
         */
        private String suit;
        /**
         * 纪年
         */
        private String lunarYear;
        /**
         * 农历
         */
        private String lunar;
        /**
         * 年份和月份
         */
        @JSONField(name = "year-month")
        private String yearMonth;
        /**
         * 具体日期
         */
        private String date;
    }

    /**
     * 近期假期
     */
    @Data
    private static class NearHoliday {
        /**
         * 年份
         */
        private String year;
        /**
         * 年份和月份
         */
        @JSONField(name = "year-month")
        private String yearMonth;
        /**
         * 当月近期假日
         */
        private String holiday;
        /**
         * 当月近期假期(数组类型)
         */
        @JSONField(name = "holiday_array")
        private List<Holiday> holidayArray;
    }

    /**
     * 假期
     */
    @Data
    private static class Holiday {
        /**
         * 假期名
         */
        private String name;
        /**
         * 假期第一天日期
         */
        private String festival;
        /**
         * 描述
         */
        private String desc;
        /**
         * 休息建议
         */
        private String rest;
        /**
         * 放假天数
         */
        @JSONField(name = "list_num")
        private Long listNum;
        /**
         * 假期日期 list
         */
        private List<HolidayDay> list;
    }

    /**
     * 假期日
     */
    @Data
    private static class HolidayDay {
        /**
         * 日期
         */
        private String date;
        /**
         * 状态 1:放假, 2:上班
         */
        private Integer status;
    }

    public static void main(String[] args) throws ParseException {

        RestTemplate restTemplate = new RestTemplate();
        LocalDate localDate = DateUtil.strToLocalDate("2021-07-16", DateUtil.COMMON_DATE_FORMAT);
        for (LocalDate d = localDate; d.isBefore(localDate.plusYears(1)); d = d.plusDays(1)) {
            String date = DateUtil.formatTime(d, "yyyy-MM-dd");
            String res = restTemplate.getForObject("http://v.juhe.cn/calendar/day?date=" + StringUtils.replaceAll(date, "-0", "-") + "&key=e5af5062813363cf63a36852394b0f87", String.class);
            writeLine("C:\\Users\\徐富豪\\Desktop\\cal.txt", res);
        }
    }

    public static void writeLine(String path, String line) {
        try {
            Files.write(Paths.get(path), (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

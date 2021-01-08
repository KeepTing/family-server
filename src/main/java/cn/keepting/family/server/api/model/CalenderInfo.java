package cn.keepting.family.server.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.api.model
 * @date:2021/1/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalenderInfo {
    private String animalsYear;
    private String weekday;
    private String lunarYear;
    private String lunar;
    @JSONField(name = "year-month")
    private String yearMonth;
    private String date;
    private String suit;
    private String avoid;
    private String holiday;
    private String desc;
}

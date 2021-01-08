package cn.keepting.family.server.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao.model
 * @date:2021/1/7
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("family_calender")
public class CalenderPo {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 天
     */
    private String day;

    /**
     * 宜
     */
    private String suit;

    /**
     * 忌
     */
    private String avoid;

    /**
     * 周几
     */
    private String weekend;
    private Integer weekendNum;

    /**
     * 阴历
     */
    private String lunar;

    /**
     * 节日
     */
    private String holiday;

    /**
     * 描述
     */
    private String desc;

    /**
     * 阴历年份
     */
    private String lannarYear;
    private String animal;
}

package cn.keepting.family.server.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao.model
 * @date:2021/1/11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("hefeng_city")
public class HefengCityPo {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 一级行政区划拼音
     */
    private String firstAdmEn;

    /**
     * 一级行政区划名
     */
    private String firstAdmName;

    /**
     * 二级行政区划拼音
     */
    private String secAdmEn;

    /**
     * 二级行政区划名
     */
    private String secAdmName;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;
}

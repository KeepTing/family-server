package cn.keepting.family.server.dao.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao.model
 * @date:2021/1/8
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("city")
public class CityPo {
    /**
     * id
     */
    private String id;

    /**
     * cityen
     */
    private String cityen;

    /**
     * cityzh
     */
    private String cityzh;

    /**
     * provinceen
     */
    private String provinceen;

    /**
     * provincezh
     */
    private String provincezh;

    /**
     * countryen
     */
    private String countryen;

    /**
     * countryzh
     */
    private String countryzh;

    /**
     * leaderen
     */
    private String leaderen;

    /**
     * leaderzh
     */
    private String leaderzh;

    /**
     * lat
     */
    private String lat;

    /**
     * lon
     */
    private String lon;
}

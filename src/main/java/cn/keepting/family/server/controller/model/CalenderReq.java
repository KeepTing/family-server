package cn.keepting.family.server.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.controller.model
 * @date:2021/1/7
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalenderReq {

    @ApiModelProperty("日期（yyyy-M-d）")
    private String date;
    @ApiModelProperty("月份（1-12）")
    private String month;

    @ApiModelProperty("搜索类型：0：全年，1：按月，2：按天")
    private Integer searchType;
}

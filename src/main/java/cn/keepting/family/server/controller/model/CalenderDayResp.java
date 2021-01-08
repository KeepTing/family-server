package cn.keepting.family.server.controller.model;

import cn.keepting.family.server.controller.dto.CalenderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.controller.model
 * @date:2021/1/7
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalenderDayResp {
    CalenderDTO cal;
}

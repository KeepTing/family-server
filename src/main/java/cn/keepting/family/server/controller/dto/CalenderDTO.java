package cn.keepting.family.server.controller.dto;

import cn.keepting.family.server.dao.model.CalenderPo;
import io.swagger.annotations.ApiModel;
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
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalenderDTO {

    String date;
    String year;
    String month;
    String day;
    String suit;
    String avoid;
    String holiday;
    String lunar;
    String weekend;
    Integer weekendNum;

    public static CalenderDTO copyFromPo(CalenderPo po) {
        return CalenderDTO.builder()
                .date(po.getYear() + "-" + po.getMonth() + "-" + po.getDay())
                .day(po.getDay())
                .year(po.getYear())
                .month(po.getMonth())
                .suit(po.getSuit())
                .holiday(po.getHoliday())
                .lunar(po.getLunar())
                .avoid(po.getAvoid())
                .weekend(po.getWeekend())
                .weekendNum(po.getWeekendNum())
                .build();
    }
}

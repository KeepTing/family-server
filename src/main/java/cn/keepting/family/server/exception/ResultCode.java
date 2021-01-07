package cn.keepting.family.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS("00", ""),

    FAILURE("555", "业务异常"),
    CANCEL_ORSER_OVERLIMIY("666", "取消订单超限，当天不能下单"),

    BAD_REQUEST("400", "参数错误"),

    LOGIN_ERROR("999", "请重新登录"),
    NOT_USER("888", "用户不存在"),

    INTERNAL_SERVER_ERROR("500", "服务器出了一个问题");

    public String status;

    public String errmsg;
}

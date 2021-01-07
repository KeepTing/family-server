package cn.keepting.family.server.constant;

import cn.keepting.family.server.exception.ResultCode;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    @ApiModelProperty(value = "状态码")
    private String status = ResultCode.SUCCESS.status;

    private String errmsg;

    private T data;

    public static BaseResponse<Object> error(String status, String errmsg) {
        return new BaseResponse(status, errmsg, new HashMap<>());
    }

    public static <V> BaseResponse<V> ok(V data) {
        return new BaseResponse<>(ResultCode.SUCCESS.status, "", data);
    }

    public static BaseResponse ok() {
        return new BaseResponse(ResultCode.SUCCESS.status, "", new HashMap<>());
    }

    @ApiModelProperty(hidden = true)
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return StringUtils.equals(status, ResultCode.SUCCESS.status);
    }
}

package us.dev.shipandcargo.domain;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import us.dev.shipandcargo.enums.ApiCode;
import us.dev.shipandcargo.enums.ApiMessage;

@ApiModel(value = "Result", description = "result object")
public class Result<T> {
    @ApiModelProperty(example = "0", value = "result code")
    private Integer code;
    @ApiModelProperty(example = "success", value = "result message")
    private String msg;

//    @ApiModelProperty(example = "0af7651916cd43dd8448eb211c80319c", value = "trace id")
//    private String trackingId;
    // @ApiModelProperty(example = "T", value = "result data")

    private T data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        // this.trackingId = TraceUtil.getTraceId();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public String getTrackingId() {
//        return trackingId;
//    }
//
//    public void setTrackingId(String trackingId) {
//        this.trackingId = trackingId;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success() {
        return success(ApiMessage.SUCCESS.getMsg(), new JSONObject());
    }

    public static <T> Result<T> success(T data) {
        return success(ApiMessage.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ApiCode.OK.getCode(), msg, data);
    }

    public static Result failure(ApiMessage apiMessage, String msg) {
        return new Result(apiMessage.getCode(), msg, new JSONObject());
    }

    public static Result failure(ApiMessage apiMessage) {
        return new Result(apiMessage.getCode(), apiMessage.getMsg(), new JSONObject());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

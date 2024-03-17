package us.dev.shipandcargo.util;

import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.enums.ApiCode;
import us.dev.shipandcargo.enums.ApiMessage;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ApiCode.OK.getCode());
        result.setMsg(ApiMessage.SUCCESS.getMsg());
        // result.setTrackingId(TraceUtil.getTraceId());
        result.setData(object);
        return result;
    }

    public static Result error(Integer code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        // result.setTrackingId(TraceUtil.getTraceId());
        return result;
    }

    public static Result error(ApiMessage apiMessage) {
        Result result = new Result();
        result.setCode(apiMessage.getCode());
        result.setMsg(apiMessage.getMsg());
        // result.setTrackingId(TraceUtil.getTraceId());
        return result;
    }

}

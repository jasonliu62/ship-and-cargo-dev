package us.dev.shipandcargo.exception;

import us.dev.shipandcargo.enums.ApiMessage;

public class ApiException extends RuntimeException{

    private final Integer code;
    private Object data;

    public ApiException(ApiMessage apiMessage) {
        super(apiMessage.getMsg());
        this.code = apiMessage.getCode();
    }

    public Integer getCode() {return code;}
    public Object getData() {return data;}

}

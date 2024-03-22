package us.dev.shipandcargo.enums;

public enum ApiMessage {
    SUCCESS(0, "SUCCESS"),
    DELETE_FAIL_USER_NA(500, "Delete failed, user does not exist"),
    ILLEGAL_PARAMS(3000, "Illegal request parameters"),
    EMAIL_ALREADY_REG(5000, "This email address has already been registered."),
    EMAIL_FORMAT_INVALID(5001, "This email format is invalid."),
    LOGIN_ERROR(6000, "Email or Password Incorrect."),
    SHIP_EXISTED(7000, "Ship already existed"),
    SHIP_MANAGEMENT_EXISTED(7001, "Ship Management already existed");

    private int code;
    private String msg;

    ApiMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {return code;}
    public String getMsg() {return msg;};
}

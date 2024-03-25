package us.dev.shipandcargo.enums;

public enum ApiMessage {
    SUCCESS(0, "SUCCESS"),
    DELETE_FAIL_USER_NA(500, "Delete failed, user does not exist"),
    CARGO_EXISTED(2000, "Cargo already existed"),
    ILLEGAL_PARAMS(3000, "Illegal request parameters"),
    EMAIL_ALREADY_REG(5000, "This email address has already been registered."),
    EMAIL_FORMAT_INVALID(5001, "This email format is invalid."),
    LOGIN_ERROR(6000, "Email or Password Incorrect."),
    SHIP_EXISTED(7000, "Ship already existed"),
    SHIP_MANAGEMENT_EXISTED(7001, "Ship Management already existed"),
    SHIP_MISSION_LOCATION_EXISTED(7002, "Ship with mission's location already existed"),
    SHIP_IDLE_LOCATION_EXISTED(7003, "Ship idle location already existed"),
    PORT_EXISTED(8000, "Port already existed"),
    PORT_ACTIVITY_EXISTED(8001, "Port activity already existed");


    private int code;
    private String msg;

    ApiMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {return code;}
    public String getMsg() {return msg;};
}

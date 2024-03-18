package us.dev.shipandcargo.enums;

public enum CargoDisplayEnum {
    //"实际"
    ACTUAL(0),
    //"虚拟"
    VIRTUAL(1),
    //"即期"
    SPOT(2);

    private int code;
    CargoDisplayEnum(int code) { this.code = code; }
    public int getCode() {
        return code;
    }
}

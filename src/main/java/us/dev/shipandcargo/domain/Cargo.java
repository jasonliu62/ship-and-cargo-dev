package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Cargo {

    private Long contractNumber;
    private String contractType;
    private Long cargoId;
    private Float cargoVolume;
    private String cargoType;
    // 从苏州运到休斯顿
    private String cargoflowArea;
    private String loadPortId;
    private String unloadPortId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime layDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dischargeDay;

    // 运费率
    private Float freightRate;
    // 载运差价
    private Float volumeRate;
    private Float loadportDepth;
    private Float unloadportDepth;
    private Long voyageNumber;
    private Float voyageVolume;

    private Float voyagePeriod;

    // private String company;
    // private 0: 实际，1: 虚拟，2: 即期
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

package us.dev.shipandcargo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoNeed {
    private Long contractNumber;
    private Long cargoId;

    private Float loadCost;
    private Float unloadCost;
    private Float Other;
    private Float loadTime;
    private Float unloadTime;
    private Float anchorTime;
    private Float irregularstoppingTime;
    private Float navigationTime;
    private Float voyageTime;

    // private 0: 实际，1: 虚拟，2: 即期
    private int status;
    private Long uploaderId;
}

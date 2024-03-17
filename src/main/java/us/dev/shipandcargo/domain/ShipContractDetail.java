package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ShipContractDetail {

    private Long imo;
    private Long contractId;
    private Long cargoVolume;
    // enum?
    private String cargoType;
    // enum? 下拉菜单 enum
    private String contractType;
    private Long loadPortId;
    private Long unloadPortId;
    // 运载率，这边是%还是0.x
    private Long frightRate;
    // 货运量运载差率
    private Long volumeRate;
    // 约定运载航运次数
    private Long voyageNumber;
    // 单航次载运量
    private Long voyageVolume;
    // 载运周期
    private Long voyagePeriod;
    // 约定装货时间
    private Long layDay;
    // 约定卸货时间
    private Long dischargeDay;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

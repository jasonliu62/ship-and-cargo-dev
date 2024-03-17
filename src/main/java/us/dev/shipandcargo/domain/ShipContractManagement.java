package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShipContractManagement {

    private Long imo;
    private Long contractId;
    // 预估和实际利润
    private Long estiProfit;
    private Long realProfit;
    // 合同进度
    private Long contractProgress;
    // 有几种？
    private String contractStatus;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

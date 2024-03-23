package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContractManagement {

    // 货物合同编号
    // 货物合同的利润

    private Long id;
    private Long contractNumber;
    // 预估和实际利润
    private Long contractType;
    private Long predictingBenefit;
    private Long actualBenefit;
    // 合同进度
    private Long contractProgress;
    // 有几种？
    private String contractState;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

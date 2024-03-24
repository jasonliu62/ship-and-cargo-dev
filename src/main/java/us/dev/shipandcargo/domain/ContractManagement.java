package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContractManagement {

    // 模拟运算的id，不写成自增：
    private Long id;

    private Long contractNumber;
    // 预估和实际利润
    private String contractType;
    private Long cargoId;
    private Float predictingBenefit;
    private Float actualBenefit;
    // 合同进度 (完成 or 未完成)
    private String contractProgress;
    private String contractState;
    // private 0: 实际，1: 虚拟，2: 即期
    private int status;
    private Long uploaderId;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

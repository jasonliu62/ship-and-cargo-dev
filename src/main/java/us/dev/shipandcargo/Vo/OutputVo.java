package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "OutputVo", description = "Basic info of the output")
public class OutputVo {
    private Long groupId;
    private Long id;
    // 实际：actual
    // 虚拟: virtual
    // 即期: spot
    private Float actual_own;
    private Float actual_rent;
    private Float actual_all;
    private Float virtual_own;
    private Float virtual_rent;
    private Float virtual_all;
    private Float spot_own;
    private Float spot_rent;
    private Float spot_all;
    private Float total;
}

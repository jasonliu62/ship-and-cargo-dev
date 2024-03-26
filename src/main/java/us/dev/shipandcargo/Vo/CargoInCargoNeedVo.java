package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "CargoInCargoNeedVo", description = "Basic info of the cargo on cargo Need, 为cargoNeed页面的cargo数据展示")
public class CargoInCargoNeedVo {
    private Long contractNumber;
    private Long cargoId;
    private String loadPortId;
    private String unloadPortId;
}

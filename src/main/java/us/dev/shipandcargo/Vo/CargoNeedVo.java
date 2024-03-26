package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "CargoNeedVo", description = "Basic info of the cargo Need")
public class CargoNeedVo {
    private Float loadCost;
    private Float unloadCost;
    private Float Other;
    private Float loadTime;
    private Float unloadTime;
    private Float anchorTime;
    private Float irregularstoppingTime;
    private Float navigationTime;
    private Float voyageTime;
}
package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "ShipEconIndicatorVo", description = "Basic info of the Ship Econ")
public class ShipEconIndicatorVo {

    private Float oilConsumption;
    private Float oilPrice;
    private String chartType;
    private Float charterTime;
    private Float dailyRent;

}

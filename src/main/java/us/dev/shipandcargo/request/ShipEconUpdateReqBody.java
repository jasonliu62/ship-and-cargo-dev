package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "ShipEconUpdateReqBody", description =  "ship econ update request body")
public class ShipEconUpdateReqBody {

    @NotEmpty(message = "Oil consumption cannot be null")
    @ApiModelProperty(example = "20.5", value = "Oil consumption in tonnes per day")
    private Float oilConsumption;

    @NotEmpty(message = "Oil price cannot be null")
    @ApiModelProperty(example = "500.0", value = "Current oil price per tonne")
    private Float oilPrice;

    @NotEmpty(message = "Chart type cannot be null")
    @ApiModelProperty(example = "Time Charter", value = "Type of chart (e.g., Time Charter, Voyage Charter)")
    private String chartType;

    @NotEmpty(message = "Charter time cannot be null")
    @ApiModelProperty(example = "365", value = "Charter duration in days")
    private Float charterTime;

    @NotEmpty(message = "Daily rent cannot be null")
    @ApiModelProperty(example = "15000.0", value = "Daily rent price")
    private Float dailyRent;

}

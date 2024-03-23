package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "ShipIdleLocationInsertReqBody", description = "Ship idle location insert request body")
public class ShipIdleLocationInsertReqBody {
    @ApiModelProperty(example = "123456789", value = "IMO number")
    @NotNull(message = "Please provide the IMO number")
    private Long imo;

    @ApiModelProperty(example = "XYZ123", value = "port ID")
    @NotNull(message = "Please provide the port ID")
    private String portId;

}

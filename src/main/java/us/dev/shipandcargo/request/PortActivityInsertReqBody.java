package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "PortActivityInsertReqBody", description =  "port activity insert request body")
public class PortActivityInsertReqBody {

    @ApiModelProperty(example = "123456789", value = "Port ID")
    @NotEmpty(message = "Please provide Port Id")
    private String portId;

    @ApiModelProperty(example = "123", value = "Ship Amount")
    @NotEmpty(message = "Please provide Ship Amount")
    private Long shipAmount;

}

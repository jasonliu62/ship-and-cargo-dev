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
@ApiModel(value = "PortInsertReqBody", description =  "port insert request body")
public class PortInsertReqBody {
    @ApiModelProperty(example = "123456789", value = "Port ID")
    @NotEmpty(message = "Please provide Port Id")
    private String portId;

    @ApiModelProperty(example = "港口中文名称", value = "Chinese name of the port")
    private String nameCHN;

    @ApiModelProperty(example = "Port English Name", value = "English name of the port")
    private String nameENG;

    @ApiModelProperty(example = "-75.1467", value = "Latitude of the port")
    private String latitude;

    @ApiModelProperty(example = "39.9526", value = "Longitude of the port")
    private String longitude;

    @ApiModelProperty(example = "10.5", value = "Minimum draft of the port")
    private Float minDraft;
}

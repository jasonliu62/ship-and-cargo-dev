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
@ApiModel(value = "ShipInsertReqBody", description =  "ship insert request body")
public class ShipInsertReqBody {

    @ApiModelProperty(example = "123456789", value = "IMO number")
    @NotNull(message = "Please provide the IMO number")
    private Long imo;

    @ApiModelProperty(example = "150000", value = "Deadweight tonnage")
    @NotNull(message = "Please provide the dead weight")
    private Float deadWeight;

    @ApiModelProperty(example = "Bulk Carrier", value = "Type of ship")
    @NotEmpty(message = "Please provide the ship type")
    private String shipType;

    @ApiModelProperty(example = "Ocean Giant", value = "Name of ship")
    @NotEmpty(message = "Please provide the ship name")
    private String shipName;

    @ApiModelProperty(example = "5000", value = "Ship constant weight")
    @NotNull(message = "Please provide the ship constant")
    private Float shipConstant;

    @ApiModelProperty(example = "16", value = "Maximum draft under full load")
    @NotNull(message = "Please provide the dead draft")
    private Float deadDraft;

    @ApiModelProperty(example = "10", value = "Draft when ship is empty")
    @NotNull(message = "Please provide the empty draft")
    private Float emptyDraft;

    @ApiModelProperty(example = "12", value = "Draft when ship is in ballast")
    @NotNull(message = "Please provide the ballast draft")
    private Float ballastDraft;

    @ApiModelProperty(example = "35000", value = "Cubic capacity of ship")
    @NotNull(message = "Please provide the ship cubic")
    private Float shipCubic;

    @ApiModelProperty(example = "Liberia", value = "Flag state of the ship")
    @NotEmpty(message = "Please provide the flag state")
    private String flagState;

    @ApiModelProperty(example = "ABC123", value = "Unique tag for the ship")
    @NotEmpty(message = "Please provide the ship tag")
    private String shipTag;

    @ApiModelProperty(example = "Global Shipping LLC", value = "Owner of the ship")
    @NotEmpty(message = "Please provide the owner")
    private String owner;

    @ApiModelProperty(example = "Oceanic Operations", value = "Operator of the ship")
    @NotEmpty(message = "Please provide the operator")
    private String operator;

}

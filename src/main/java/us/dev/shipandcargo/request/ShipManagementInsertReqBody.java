package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "ShipManagementReqBody", description = "Ship management request body")
public class ShipManagementInsertReqBody {
    @ApiModelProperty(example = "123456789", value = "IMO number")
    @NotNull(message = "Please provide the IMO number")
    private Long imo;

    @ApiModelProperty(example = "150.5", value = "Ship operating day")
    @NotEmpty(message = "Please fill")
    private Float shipOperatingDay;

    @ApiModelProperty(example = "250.75", value = "Ship total time")
    @NotEmpty(message = "Please fill")
    private Float shipTotalTime;

    @ApiModelProperty(example = "1000.0", value = "Daily capital cost")
    @NotEmpty(message = "Please fill")
    private Float dailyCapitalCost;

    @ApiModelProperty(example = "800.0", value = "Daily operating cost")
    @NotEmpty(message = "Please fill")
    private Float dailyOperatingCost;

    @ApiModelProperty(example = "Bulk Cargo", value = "Acceptable cargo type")
    @NotEmpty(message = "Please provide the acceptable cargo type")
    private String acceptableCargoType;

    @ApiModelProperty(example = "Global Shipping LLC", value = "Owner")
    @NotEmpty(message = "Please provide the owner")
    private String owner;

    @ApiModelProperty(example = "Oceanic Operations", value = "Operator")
    @NotEmpty(message = "Please provide the operator")
    private String operator;

    @ApiModelProperty(example = "Pacific Ocean", value = "Navigation area")
    @NotEmpty(message = "Please provide the navigation area")
    private String navigationArea;

    @ApiModelProperty(example = "Engineering", value = "Ship department")
    @NotEmpty(message = "Please provide the ship department")
    private String shipDepartment;

    @ApiModelProperty(example = "Port XYZ", value = "Ship empty port")
    @NotEmpty(message = "Please provide the ship empty port")
    private String shipEmptyPort;

    @ApiModelProperty(example = "2023-01-15 10:00:00", value = "Ship empty time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipEmptyTime;

}

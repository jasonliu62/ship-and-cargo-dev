package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "ShipActiveMissionLocationInsertReqBody", description = "Ship active mission location insert request body")
public class ShipActiveMissionLocationInsertReqBody {
    @ApiModelProperty(example = "123456789", value = "IMO number")
    @NotNull(message = "Please provide the IMO number")
    private Long imo;

    @ApiModelProperty(example = "-75.1467", value = "Latitude")
    @NotNull(message = "Please provide the latitude")
    private Float latitude;

    @ApiModelProperty(example = "39.9526", value = "Longitude")
    @NotNull(message = "Please provide the longitude")
    private Float longitude;

    @ApiModelProperty(example = "XYZ123", value = "Previous port ID")
    @NotNull(message = "Please provide the previous port ID")
    private String postPortId;

    @ApiModelProperty(example = "ABC456", value = "Next port ID")
    @NotNull(message = "Please provide the next port ID")
    private String nextPortId;

    @ApiModelProperty(example = "22", value = "Speed")
    @NotNull(message = "Please provide the speed")
    private Float speed;

    @ApiModelProperty(example = "500", value = "Distance from the departure port")
    @NotNull(message = "Please provide the departure distance")
    private Float departureDistance;

    @ApiModelProperty(example = "300", value = "Distance to the arrival port")
    @NotNull(message = "Please provide the arrival distance")
    private Float arrivalDistance;

    @ApiModelProperty(example = "2023-01-15 10:00:00", value = "current time")
    @NotNull(message = "Please provide the current time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currentTime;

}

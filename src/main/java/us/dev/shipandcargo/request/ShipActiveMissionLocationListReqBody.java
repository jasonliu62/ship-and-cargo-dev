package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.request.paging.PaginationProps;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "ShipActiveMissionLocationListReqBody", description = "Ship active mission location list request body")
public class ShipActiveMissionLocationListReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "Please fill in pagination")
    private PaginationProps pagination;

    @ApiModelProperty(example = "123456789", value = "IMO number")
    private Long imo;

    @ApiModelProperty(example = "-75.1467", value = "Latitude")
    private Float latitude;

    @ApiModelProperty(example = "39.9526", value = "Longitude")
    private Float longitude;

    @ApiModelProperty(example = "XYZ123", value = "Previous port ID")
    private String postPortId;

    @ApiModelProperty(example = "ABC456", value = "Next port ID")
    private String nextPortId;

    @ApiModelProperty(example = "22", value = "Speed")
    private Float speed;

    @ApiModelProperty(example = "500", value = "Distance from the departure port")
    private Float departureDistance;

    @ApiModelProperty(example = "300", value = "Distance to the arrival port")
    private Float arrivalDistance;

    @ApiModelProperty(example = "2023-01-15 10:00:00", value = "current time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currentTime;

}

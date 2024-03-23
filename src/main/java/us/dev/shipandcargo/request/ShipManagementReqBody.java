package us.dev.shipandcargo.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.request.paging.PaginationProps;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
public class ShipManagementReqBody {

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

    @ApiModelProperty(example = "150.5", value = "Ship operating day")
    private Float shipOperatingDay;

    @ApiModelProperty(example = "250.75", value = "Ship total time")
    private Float shipTotalTime;

    @ApiModelProperty(example = "1000.0", value = "Daily capital cost")
    private Float dailyCapitalCost;

    @ApiModelProperty(example = "800.0", value = "Daily operating cost")
    private Float dailyOperatingCost;

    @ApiModelProperty(example = "Bulk Cargo", value = "Acceptable cargo type")
    private String acceptableCargoType;

    @ApiModelProperty(example = "Global Shipping LLC", value = "Owner")
    private String owner;

    @ApiModelProperty(example = "Oceanic Operations", value = "Operator")
    private String operator;

    @ApiModelProperty(example = "Pacific Ocean", value = "Navigation area")
    private String navigationArea;

    @ApiModelProperty(example = "Engineering", value = "Ship department")
    private String shipDepartment;

    @ApiModelProperty(example = "Port XYZ", value = "Ship empty port")
    private String shipEmptyPort;

    @ApiModelProperty(example = "2023-01-15 10:00:00", value = "Ship empty time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipEmptyTime;

}

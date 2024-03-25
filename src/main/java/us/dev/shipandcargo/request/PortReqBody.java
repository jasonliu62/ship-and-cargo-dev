package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.request.paging.PaginationProps;

@Data
@Getter
@Setter
@ApiModel(value = "PortReqBody", description = "Port request body")
public class PortReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "Please fill in pagination")
    private PaginationProps pagination;

    @ApiModelProperty(example = "123456789", value = "Port ID")
    private Long portId;

    @ApiModelProperty(example = "港口中文名称", value = "Chinese name of the port")
    private String nameCHN;

    @ApiModelProperty(example = "Port English Name", value = "English name of the port")
    private String nameENG;

    @ApiModelProperty(example = "-75.1467", value = "Latitude of the port")
    private Float latitude;

    @ApiModelProperty(example = "39.9526", value = "Longitude of the port")
    private Float longitude;

    @ApiModelProperty(example = "10.5", value = "Minimum draft of the port")
    private Float minDraft;
}

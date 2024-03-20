package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.request.paging.PaginationProps;

@Data
@Getter
@Setter
public class ShipReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "请填写")
    private PaginationProps pagination;

    @ApiModelProperty(example = "123456789", value = "IMO number")
    private Long imo;

    @ApiModelProperty(example = "150000", value = "Deadweight tonnage")
    private Float deadWeight;

    @ApiModelProperty(example = "Bulk Carrier", value = "Type of ship")
    private String shipType;

    @ApiModelProperty(example = "Ocean Giant", value = "Name of ship")
    private String shipName;

    @ApiModelProperty(example = "5000", value = "Ship constant weight")
    private Float shipConstant;

    @ApiModelProperty(example = "16", value = "Maximum draft under full load")
    private Float deadDraft;

    @ApiModelProperty(example = "10", value = "Draft when ship is empty")
    private Float emptyDraft;

    @ApiModelProperty(example = "12", value = "Draft when ship is in ballast")
    private Float ballastDraft;

    @ApiModelProperty(example = "35000", value = "Cubic capacity of ship")
    private Float shipCubic;

    @ApiModelProperty(example = "Liberia", value = "Flag state of the ship")
    private String flagState;

    @ApiModelProperty(example = "ABC123", value = "Unique tag for the ship")
    private String shipTag;

    @ApiModelProperty(example = "Global Shipping LLC", value = "Owner of the ship")
    private String owner;

    @ApiModelProperty(example = "Oceanic Operations", value = "Operator of the ship")
    private String operator;

}

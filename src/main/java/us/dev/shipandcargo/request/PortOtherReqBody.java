package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.request.paging.PaginationProps;

@Data
@Getter
@Setter
@ApiModel(value = "PortOtherReqBody", description = "Port other request body")
public class PortOtherReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "请填写")
    private PaginationProps pagination;

    @ApiModelProperty(example = "1002", value = "港口编号")
    private Long portId;

    @ApiModelProperty(example = "1200", value = "第一个航段的里程, 海里")
    private Float distance;

    @ApiModelProperty(example = "500", value = "油价")
    private Float oilPrice;

    @ApiModelProperty(example = "24", value = "平均停留时间")
    private Float avgTimeStay;

    @ApiModelProperty(example = "3", value = "装船时间")
    private Float loadTime;

    @ApiModelProperty(example = "0.8", value = "装船效率")
    private Float loadEfficiency;

    @ApiModelProperty(example = "4", value = "卸船时间")
    private Float unloadTime;

    @ApiModelProperty(example = "0.75", value = "卸船效率")
    private Float unloadEfficiency;

    @ApiModelProperty(example = "2000", value = "港口费")
    private Float portFee;

    @ApiModelProperty(example = "12", value = "平均锚泊时间")
    private Float avgAnchorTime;


}

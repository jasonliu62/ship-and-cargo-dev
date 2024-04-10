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
@ApiModel(value = "PaginationReqBody", description =  "onlyForPagination")
public class PaginationReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "请填写")
    private PaginationProps pagination;

}

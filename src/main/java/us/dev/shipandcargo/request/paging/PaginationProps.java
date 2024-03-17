package us.dev.shipandcargo.request.paging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.util.StringUtil;

/**
 * To seamlessly integrate with Ant Design List or Table List, we would like to replace <code>PaginationQuery</code>
 * with <code>PaginationProps</code>
 *
 * @author allan
 * @date 2021/10/27
 */
@Data
@NoArgsConstructor
@ApiModel(value = "PaginationProps", description = "The common query parameters of paging list APIs")
public class PaginationProps {

    @Min(1)
    @ApiModelProperty(example = "1", value = "Current page number, the default value is 1.")
    private int current = 1;

    @Max(2000)
    @ApiModelProperty(example = "50", value = "The optional page size: default=100,  max=2000")
    private int pageSize = 100;

    @ApiModelProperty(example = "createdAt:desc",
            value = "The optional sort orders, ie. name:asc, createdAt:desc, id:descend, uuid: ascend")
    private String[] sortBy;

    public List<PaginationOrderQuery> getSortByToList() {
        if (sortBy == null || sortBy.length == 0) {
            return null;
        }

        List<PaginationOrderQuery> parsedSortBy = new ArrayList<>();
        for (String sb : sortBy) {
            if (sb == null) {
                continue;
            }
            String field = sb;
            String sort = PaginationOrderQuery.ASC;
            if (sb.contains(":")) {
                String[] array = sb.split(":");
                field = array[0];
                sort = array[1].trim();
                if (PaginationOrderQuery.ASC.equalsIgnoreCase(sort)
                        || PaginationOrderQuery.ASCEND.equalsIgnoreCase(sort)) {
                    sort = PaginationOrderQuery.ASC;
                } else if (PaginationOrderQuery.DESC.equalsIgnoreCase(sort)
                        || PaginationOrderQuery.DESCEND.equalsIgnoreCase(sort)) {
                    sort = PaginationOrderQuery.DESC;
                } else {
                    throw new ApiException(ApiMessage.ILLEGAL_PARAMS);
                }
            }

            field = StringUtil.humpToLine(field);
            parsedSortBy.add(new PaginationOrderQuery(field, sort));

        }
        return parsedSortBy;
    }

}


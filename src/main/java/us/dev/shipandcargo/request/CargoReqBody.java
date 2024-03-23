package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "CargoReqBody", description =  "cargo request body")
public class CargoReqBody {

    @ApiModelProperty(example = "{\"current\":1,\"pageSize\":50,\"sortBy\":[\"createdAt:desc\"]}",
            value = "Pagination and sorting parameters. " +
                    "'current' is the current page number with a default of 1; " +
                    "'pageSize' is the number of items per page with a default of 100 and a maximum of 2000; " +
                    "'sortBy' is an array of strings defining the order of sorting, each string formatted as 'fieldName:direction' where 'direction' can be 'asc' for ascending or 'desc' for descending."
    )
    @NotEmpty(message = "请填写")
    private PaginationProps pagination;

    @ApiModelProperty(example = "1001", value = "合同编号")
    private Long contractNumber;

    @ApiModelProperty(example = "1001", value = "货物编号")
    private Long cargoId;

    @ApiModelProperty(example = "铁矿石合同", value = "合同种类")
    private String contractType;

    @ApiModelProperty(example = "500", value = "货物体积")
    private Float cargoVolume;

    @ApiModelProperty(example = "粮食", value = "货物类型")
    private String cargoType;

    @ApiModelProperty(example = "苏州", value = "货物流动区域")
    private String cargoflowArea;

    @ApiModelProperty(example = "悉尼", value = "装货港ID")
    private String loadPortId;

    @ApiModelProperty(example = "奥克兰", value = "卸货港ID")
    private String unloadPortId;

    @ApiModelProperty(example = "2023-10-10 20:20:20", value = "装货日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime layDay;

    @ApiModelProperty(example = "2023-10-10 20:20:20", value = "卸货日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dischargeDay;

    @ApiModelProperty(example = "0.5", value = "运费率")
    private Float freightRate;

    @ApiModelProperty(example = "0.3", value = "载运差价")
    private Float volumeRate;

    @ApiModelProperty(example = "10", value = "装货港深度")
    private Float loadportDepth;

    @ApiModelProperty(example = "15", value = "卸货港深度")
    private Float unloadportDepth;

    @ApiModelProperty(example = "100", value = "航次编号")
    private Long voyageNumber;

    @ApiModelProperty(example = "1000", value = "航次体积")
    private Float voyageVolume;

    @ApiModelProperty(example = "880", value = "小时")
    private Float voyagePeriod;

    @ApiModelProperty(example = "0, 1, 2", value = "货物显示状态")
    @NotEmpty(message = "请填写")
    private int status;


}

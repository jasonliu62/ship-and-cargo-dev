package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "CargoInsertReqBody", description =  "cargo insert request body")
public class CargoInsertReqBody {

    @ApiModelProperty(example = "1001", value = "合同编号")
    @NotEmpty(message = "请填写")
    private Long contractNumber;

    @ApiModelProperty(example = "1001", value = "货物编号")
    @NotEmpty(message = "请填写")
    private Long cargoId;

    @ApiModelProperty(example = "铁矿石运输", value = "合同种类")
    @NotEmpty(message = "请填写")
    private String contractType;

    @ApiModelProperty(example = "500", value = "货物体积")
    @NotEmpty(message = "请填写")
    private Float cargoVolume;

    @ApiModelProperty(example = "粮食", value = "货物类型")
    @NotEmpty(message = "请填写")
    private String cargoType;

    @ApiModelProperty(example = "苏州", value = "货物流动区域")
    @NotEmpty(message = "请填写")
    private String cargoflowArea;

    @ApiModelProperty(example = "悉尼", value = "装货港ID")
    @NotEmpty(message = "请填写")
    private String loadPortId;

    @ApiModelProperty(example = "奥克兰", value = "卸货港ID")
    @NotEmpty(message = "请填写")
    private String unloadPortId;

    @ApiModelProperty(example = "2023-10-10 20:20:20", value = "装货日期")
    @NotEmpty(message = "请填写")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime layDay;

    @ApiModelProperty(example = "2023-10-10 20:20:20", value = "卸货日期")
    @NotEmpty(message = "请填写")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dischargeDay;

    @ApiModelProperty(example = "0.5", value = "运费率")
    @NotEmpty(message = "请填写")
    private Float freightRate;

    @ApiModelProperty(example = "0.3", value = "载运差价")
    @NotEmpty(message = "请填写")
    private Float volumeRate;

    @ApiModelProperty(example = "10", value = "装货港深度")
    @NotEmpty(message = "请填写")
    private Float loadportDepth;

    @ApiModelProperty(example = "15", value = "卸货港深度")
    @NotEmpty(message = "请填写")
    private Float unloadportDepth;

    @ApiModelProperty(example = "100", value = "航次编号")
    @NotEmpty(message = "请填写")
    private Long voyageNumber;

    @ApiModelProperty(example = "1000", value = "航次体积")
    @NotEmpty(message = "请填写")
    private Float voyageVolume;

    @ApiModelProperty(example = "880", value = "小时")
    @NotEmpty(message = "请填写")
    private Float voyagePeriod;

    @ApiModelProperty(example = "0: ACTUAL, 1, 2", value = "货物显示状态")
    @NotEmpty(message = "请填写")
    private int status;


}

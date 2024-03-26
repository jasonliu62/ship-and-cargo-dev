package us.dev.shipandcargo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "CargoNeedUpdateReqBody", description =  "cargo need update request body")
public class CargoNeedUpdateReqBody {

    @ApiModelProperty(example = "300", value = "装货成本")
    @NotNull(message = "装货成本不能为空")
    private Float loadCost;

    @ApiModelProperty(example = "250", value = "卸货成本")
    @NotNull(message = "卸货成本不能为空")
    private Float unloadCost;

    @ApiModelProperty(example = "100", value = "其他成本")
    @NotNull(message = "其他成本不能为空")
    private Float other;

    @ApiModelProperty(example = "48", value = "装货时间")
    @NotNull(message = "装货时间不能为空")
    private Float loadTime;

    @ApiModelProperty(example = "36", value = "卸货时间")
    @NotNull(message = "卸货时间不能为空")
    private Float unloadTime;

    @ApiModelProperty(example = "12", value = "锚泊时间")
    @NotNull(message = "锚泊时间不能为空")
    private Float anchorTime;

    @ApiModelProperty(example = "5", value = "非正常停航时间")
    @NotNull(message = "非正常停航时间不能为空")
    private Float irregularStoppingTime;

    @ApiModelProperty(example = "240", value = "航行时间")
    @NotNull(message = "航行时间不能为空")
    private Float navigationTime;

    @ApiModelProperty(example = "360", value = "航程时间")
    @NotNull(message = "航程时间不能为空")
    private Float voyageTime;

}

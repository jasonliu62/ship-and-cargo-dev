package us.dev.shipandcargo.Vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "OutputEachVo", description = "Basic info of the outputEach")
public class OutputEachVo {

    private Long groupId;
    private Long outputId;
    private Long cargoId;
    private String cargoType;
    private String shipCombo;
    private Float profit;

}

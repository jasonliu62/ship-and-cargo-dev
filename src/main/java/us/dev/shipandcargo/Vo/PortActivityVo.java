package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "PortActivityVo", description = "Basic info of the port activity")
public class PortActivityVo {

    private String portId;
    private Long shipAmount;

}

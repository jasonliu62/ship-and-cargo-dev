package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "ShipIdleLocationVo", description = "Basic info of the Ship without mission")
public class ShipIdleLocationVo {

    private Long imo;
    private String portId;
    private Long uploaderId;

}

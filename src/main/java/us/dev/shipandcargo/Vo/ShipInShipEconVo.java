package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "ShipInShipEconVo", description = "Basic info of the Ship in Ship Econ")
public class ShipInShipEconVo {
    private Long imo;
    private String shipType;
    private String shipName;
}

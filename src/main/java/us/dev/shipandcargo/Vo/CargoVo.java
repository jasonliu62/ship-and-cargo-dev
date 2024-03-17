package us.dev.shipandcargo.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "CargoVo", description = "Basic info of the cargo")
public class CargoVo {
    private Long cargoId;
    private Long cargoVolume;
    private String cargoType;
    private Long loadPortId;
    private Long unloadPortId;
    private Long layDay;
    private String company;
    private Date createdAt;
    private Date modifiedAt;
}

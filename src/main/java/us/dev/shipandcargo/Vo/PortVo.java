package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "PortVo", description = "Basic info of the port")
public class PortVo {

    private String portId;
    private String nameCHN;
    private String nameENG;
    // 经度
    private String latitude;
    // 纬度
    private String longitude;
    // 港口最小水深
    private Float minDraft;

}

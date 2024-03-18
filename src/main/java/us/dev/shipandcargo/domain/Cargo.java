package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.enums.CargoDisplayEnum;

import java.util.Date;

@Getter
@Setter
public class Cargo {

    private Long cargoId;
    private Long cargoVolume;
    private String cargoType;
    private Long loadPortId;
    private Long unloadPortId;
    private Long layDay;
    private String company;
    // private 虚拟，实际，即期
    private CargoDisplayEnum status;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

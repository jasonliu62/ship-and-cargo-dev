package us.dev.shipandcargo.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "CargoVo", description = "Basic info of the cargo")
public class CargoVo {
    private Long contractNumber;
    private String contractType;
    private Long cargoId;
    private Float cargoVolume;
    private String cargoType;
    private String cargoflowArea;
    private String loadPortId;
    private String unloadPortId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime layDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dischargeDay;
    private Float freightRate;
    private Float volumeRate;
    private Float loadportDepth;
    private Float unloadportDepth;
    private Long voyageNumber;
    private Float voyageVolume;
    private Float voyagePeriod;
    private int status;
}

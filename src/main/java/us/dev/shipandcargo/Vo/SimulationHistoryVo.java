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
@ApiModel(value = "SimulationHistoryVo", description = "Basic info of the Simulation History")
public class SimulationHistoryVo {

    private Long groupId;
    private String cargoIdCombo;
    private String imoCombo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDay;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;

}

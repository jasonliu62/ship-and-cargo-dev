package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SimulationHistory {

    private Long groupId;
    private String cargoIdCombo;
    private String imoCombo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDay;
    private Long uploaderId;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;

}

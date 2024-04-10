package us.dev.shipandcargo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class SimulationReqBody {

    @NotEmpty(message = "请填写")
    List<ShipManagement> shipManagementList;

    @NotEmpty(message = "请填写")
    List<Ship> shipList;

    @NotEmpty(message = "请填写")
    List<ShipEconIndicator> indicatorList;

    @NotEmpty(message = "请填写")
    List<Cargo> cargoList;

    @NotEmpty(message = "请填写")
    List<CargoNeed> cargoNeedList;

    @NotEmpty(message = "请填写")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDay;

    @NotEmpty(message = "请填写")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDay;

}

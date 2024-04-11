package us.dev.shipandcargo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import us.dev.shipandcargo.domain.*;

import java.util.List;


@Data
@Getter
@Setter
public class NewAlgorithmReqBody {

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
    Long uploaderId;

    @NotEmpty(message = "请填写")
    Long groupId;
}

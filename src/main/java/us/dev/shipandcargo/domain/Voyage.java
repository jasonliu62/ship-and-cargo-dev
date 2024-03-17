package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Voyage {
    // 这边是单船只的航道，还是统称的航道
    private Long imo;
    // 航次代码
    private Long voyageId;
    private Long startPortId;
    private Long destPortId;
    // 航线，按照依次顺序
    private String route;
    // 航段距离，按照依次顺序，a -> b -> c总距离
    private String routeDistance;
    // 燃油消耗量
    private Long fuelConsumption;
    private Long unregularStoppingTime;



    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

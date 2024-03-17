package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ShipManagement {

    private Long imo;
    private Long shipOperatingDay;
    private Long dailyCapitalCost;
    private Long dailyOperatingCost;
    // 可运载货物种类，如果少的化，写成enum然后改成int
    private String acceptableCargoType;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShipEconIndicator {

    private Long imo;
    private Float oilConsumption;
    private Float oilPrice;
    private String chartType;
    private Float charterTime;
    private Float dailyRent;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}
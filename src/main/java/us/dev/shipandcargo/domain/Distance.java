package us.dev.shipandcargo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distance {

    private Long id;
    private Long startPortId;
    private Long endPortId;
    private Float distance;

}

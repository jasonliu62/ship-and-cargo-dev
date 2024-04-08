package us.dev.shipandcargo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distance {

    private Long id;
    private String shipType;
    private String startPortId;
    private String endPortId;
    private Float distance;

}

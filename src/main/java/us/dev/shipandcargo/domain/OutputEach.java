package us.dev.shipandcargo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputEach {

    private Long groupId;
    private Long outputId;
    private Long cargoId;
    private String cargoType;
    private String shipCombo;
    private Float profit;
    private Long uploaderId;

}

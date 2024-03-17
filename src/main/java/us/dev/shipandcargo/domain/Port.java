package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Port {

    private Long portId;
    private String nameCHN;
    private String nameENG;
    // 经度
    private Long latitude;
    // 纬度
    private Long longitude;
    // 港口最小水深
    private Long minDraft;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

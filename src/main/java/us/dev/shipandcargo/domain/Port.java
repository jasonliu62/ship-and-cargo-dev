package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Port {

    private String portId;
    private String nameCHN;
    private String nameENG;
    // 经度
    private String latitude;
    // 纬度
    private String longitude;
    // 港口最小水深
    private Float minDraft;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

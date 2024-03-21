package us.dev.shipandcargo.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(value = "ShipActiveMissionLocationVo", description = "Basic info of the Ship with mission")
public class ShipActiveMissionLocationVo {

    private Long imo;
    // 经度
    private Float latitude;
    // 纬度
    private Float longitude;
    // 上一驶离港
    private String postPortId;
    // 目的港
    private String nextPortId;
    // 船速
    private Float speed;
    // 距上一港口距离
    private Float departureDistance;
    // 距下一港口距离
    private Float arrivalDistance;
    // 船的实时时间
    private Long currentTime;
    private int uploaderId;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// 船舶预空时间
@Getter
@Setter
@Deprecated
public class ShipSchedule {

    private Long imo;
    // 预空开始，这边的Long的定义要思考
    private Long availableFrom;
    // 预空结束
    private Long availableUntil;
    // 预空港口
    private Long availablePortId;
    // 意向运载货物类型，要写enum吗？
    private String acceptableCargoType;
    // 船舶定额载重吨
    private Long deadWeight;
    // 发布船舶信息的公司
    private Long UserId;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

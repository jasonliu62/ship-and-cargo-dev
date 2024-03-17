package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShipLocation {

    private Long imo;
    // 港口代码
    // 如果港口代码为null，则后续的数据要有
    // 如果港口代码有，后续数据为null，（船在港口）
    private Long portId;

    // 经度
    private Long latitude;
    // 纬度
    private Long longitude;
    // 上一驶离港
    private Long postPortId;
    // 目的港
    private Long nextPortId;
    // 船速
    private Long speed;
    // 航向要记录为long还是string？
    private Long direction;
    // 时间是航行时间么？
    private Long time;
    // 距下一港口距离
    private Long remainDistance;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}

package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PortOther {

    private Long portId;
    // 第一个航段的里程, 海里
    private Float Distance;
    private Float oilPrice;
    private Float avgTimeStay;
    private Float loadTime;
    // 装船效率
    private Float loadEfficiency;
    private Float unloadTime;
    // 卸船效率
    private Float unloadEfficiency;
    // 港口费
    private Float portFee;
    // 平均锚泊时间
    private Float avgAnchorTime;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

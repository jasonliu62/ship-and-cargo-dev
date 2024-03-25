package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(value = "PortOtherVo", description = "Basic info of the Port other info")
public class PortOtherVo {

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

}

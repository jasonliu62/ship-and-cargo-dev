package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PortOther {

    private Long portId;
    private Long avgTimeStay;
    // 装船效率
    private Long loadEfficiency;
    // 卸船效率
    private Long unloadEfficiency;
    // 港口费
    private Long portFee;
    // 平均锚泊时间
    private Long avgAchorTime;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

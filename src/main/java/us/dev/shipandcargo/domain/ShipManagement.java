package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
public class ShipManagement {

    // 船舶经营信息

    private Long imo;
    private Float shipOperatingDay;
    private Float shipTotalTime;
    private Float dailyCapitalCost;
    private Float dailyOperatingCost;
    // 会存多种
    private String acceptableCargoType;
    private String owner;
    private String operator;
    private String navigationArea;
    private String shipDepartment;
    private String shipEmptyPort;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipEmptyTime;
    private Long uploaderId;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}

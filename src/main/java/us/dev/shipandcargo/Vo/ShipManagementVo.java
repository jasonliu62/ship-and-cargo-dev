package us.dev.shipandcargo.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ShipManagementVo {

    private Long imo;
    private Float shipOperatingDay;
    private Float shipTotalTime;
    private Float dailyCapitalCost;
    private Float dailyOperatingCost;
    private String acceptableCargoType;
    private String owner;
    private String operator;
    private String navigationArea;
    private String shipDepartment;
    private String shipEmptyPort;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipEmptyTime;
    private Long uploaderId;
    private Date createdAt;
    private Date modifiedAt;

}

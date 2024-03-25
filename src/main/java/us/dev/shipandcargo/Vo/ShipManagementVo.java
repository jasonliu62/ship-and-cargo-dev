package us.dev.shipandcargo.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Getter
@Setter
@ApiModel(value = "ShipManagementVo", description = "Basic info of the ship management")
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

}

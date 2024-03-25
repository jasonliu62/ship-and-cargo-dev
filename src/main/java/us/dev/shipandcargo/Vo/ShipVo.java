package us.dev.shipandcargo.Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
@ApiModel(value = "ShipVo", description = "Basic info of the ship")
public class ShipVo {

    private Long imo;
    // 船舶定额载重吨
    private Float deadWeight;
    // 如果shipType就固定几艘，此处要写成enum的形式，用int来表示，下拉菜单enum
    private String shipType;
    private String shipName;
    // 船舶常定重量
    private Float shipConstant;
    // 船舶满载吃水
    private Float deadDraft;
    // 船舶空载吃水
    private Float emptyDraft;
    // 船舶压舱吃水
    private Float ballastDraft;
    // 船舶容积
    private Float shipCubic;
    private String flagState;
    private String shipTag;
    private String owner;
    private String operator;
    private Long uploaderId;

}

package us.dev.shipandcargo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ship {

    private Long imo;
    // 船舶定额载重吨
    private Long deadWeight;
    // 如果shipType就固定几艘，此处要写成enum的形式，用int来表示，下拉菜单enum
    private String shipType;
    private String shipName;
    // 船舶常定重量
    private Long shipConstant;
    // 船舶满载吃水
    private Long deadDraft;
    // 船舶空载吃水
    private Long emptyDraft;
    // 船舶压舱吃水
    private Long ballastDraft;
    // 船舶容积
    private Long shipCubic;
    private String nation;
    private String owner;
    private String operator;
    private Long uploaderId;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;



}

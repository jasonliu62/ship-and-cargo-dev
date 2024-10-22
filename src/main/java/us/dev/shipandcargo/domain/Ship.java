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
    private Float deadWeight;
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

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;



}

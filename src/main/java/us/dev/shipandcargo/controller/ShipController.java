package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.ShipVo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.User;
import us.dev.shipandcargo.request.ShipInsertReqBody;
import us.dev.shipandcargo.request.ShipReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.ShipService;
import us.dev.shipandcargo.service.UserService;

import java.util.List;

@RestController
@Api(tags = {"Ship Module"})
@RequestMapping(value = "/ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "insert ship")
    @PostMapping("/insert")
    public Result<?> insertShip(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipInsertReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        Ship ship = new Ship();
        ship.setImo(reqBody.getImo());
        ship.setDeadWeight(reqBody.getDeadWeight());
        ship.setShipType(reqBody.getShipType());
        ship.setShipName(reqBody.getShipName());
        ship.setShipConstant(reqBody.getShipConstant());
        ship.setDeadDraft(reqBody.getDeadDraft());
        ship.setEmptyDraft(reqBody.getEmptyDraft());
        ship.setBallastDraft(reqBody.getBallastDraft());
        ship.setShipCubic(reqBody.getShipCubic());
        ship.setFlagState(reqBody.getFlagState());
        ship.setShipTag(reqBody.getShipTag());
        ship.setOwner(reqBody.getOwner());
        ship.setOperator(reqBody.getOperator());
        ship.setUploaderId(uploaderId);
        return Result.success(shipService.insertShip(ship));
    }

    @ApiOperation(value = "list ship")
    @PostMapping("/list")
    public Result<PageData<ShipVo>> listShip(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipService.listShip(reqBody.getPagination(),
                reqBody.getImo(),
                reqBody.getDeadWeight(),
                reqBody.getShipType(),
                reqBody.getShipName(),
                reqBody.getShipConstant(),
                reqBody.getDeadDraft(),
                reqBody.getEmptyDraft(),
                reqBody.getBallastDraft(),
                reqBody.getShipCubic(),
                reqBody.getFlagState(),
                reqBody.getShipTag(),
                reqBody.getOwner(),
                reqBody.getOperator(),
                uploaderId));
    }

    // delete 我还没测
    @ApiOperation(value = "delete ship")
    @PostMapping("/delete")
    public Result<?> deleteShip(@ApiParam(value = "imo", required = true) Long imo) {
        return Result.success(shipService.deleteShipByImo(imo));
    }

    // 充钱才能修改船只，懒得写了先

}

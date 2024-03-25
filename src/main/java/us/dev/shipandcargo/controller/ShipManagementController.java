package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.ShipManagementVo;
import us.dev.shipandcargo.Vo.ShipVo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.ShipManagement;
import us.dev.shipandcargo.request.ShipManagementInsertReqBody;
import us.dev.shipandcargo.request.ShipManagementReqBody;
import us.dev.shipandcargo.request.ShipReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.ShipManagementService;
import us.dev.shipandcargo.service.UserService;

@RestController
@Api(tags = {"Ship Management Module"})
@RequestMapping(value = "/ship-management")
@CrossOrigin // 儿第 记得加这个
public class ShipManagementController {

    @Autowired
    private ShipManagementService shipManagementService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "insert ship management")
    @PostMapping("/insert")

    public Result<?> insertShipManagement(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipManagementInsertReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        ShipManagement shipManagement = new ShipManagement();
        shipManagement.setImo(reqBody.getImo());
        shipManagement.setShipOperatingDay(reqBody.getShipOperatingDay());
        shipManagement.setShipTotalTime(reqBody.getShipTotalTime());
        shipManagement.setDailyCapitalCost(reqBody.getDailyCapitalCost());
        shipManagement.setDailyOperatingCost(reqBody.getDailyOperatingCost());
        shipManagement.setAcceptableCargoType(reqBody.getAcceptableCargoType());
        shipManagement.setOwner(reqBody.getOwner());
        shipManagement.setOperator(reqBody.getOperator());
        shipManagement.setNavigationArea(reqBody.getNavigationArea());
        shipManagement.setShipDepartment(reqBody.getShipDepartment());
        shipManagement.setShipEmptyPort(reqBody.getShipEmptyPort());
        shipManagement.setShipEmptyTime(reqBody.getShipEmptyTime());
        shipManagement.setUploaderId(uploaderId);
        return Result.success(shipManagementService.insertShipManagement(shipManagement));
    }
    @ApiOperation(value = "list ship management")
    @PostMapping("/list")
    public Result<PageData<ShipManagementVo>> listShipManagement(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipManagementReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipManagementService.listShipManagement(reqBody.getPagination(),
                reqBody.getImo(),
                reqBody.getShipOperatingDay(),
                reqBody.getShipTotalTime(),
                reqBody.getDailyCapitalCost(),
                reqBody.getDailyOperatingCost(),
                reqBody.getAcceptableCargoType(),
                reqBody.getOwner(),
                reqBody.getOperator(),
                reqBody.getNavigationArea(),
                reqBody.getShipDepartment(),
                reqBody.getShipEmptyPort(),
                reqBody.getShipEmptyTime(),
                uploaderId
                ));
    }

    @ApiOperation(value = "delete ship management")
    @DeleteMapping("/delete")
    public Result<?> deleteShipManagement(@ApiParam(value = "imo", required = true) Long imo) {
        return Result.success(shipManagementService.deleteShipManagementByImo(imo));
    }

}

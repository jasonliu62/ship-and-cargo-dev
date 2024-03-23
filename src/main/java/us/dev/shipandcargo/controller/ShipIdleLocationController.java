package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.ShipIdleLocationVo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.ShipIdleLocation;
import us.dev.shipandcargo.request.ShipIdleLocationInsertReqBody;
import us.dev.shipandcargo.request.ShipIdleLocationReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.ShipIdleLocationService;
import us.dev.shipandcargo.service.UserService;

@RestController
@Api(tags = {"Ship Idle Location Module"})
@RequestMapping(value = "/ship-idle-location")
public class ShipIdleLocationController {

    @Autowired
    private ShipIdleLocationService shipIdleLocationService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add new ship idle location")
    @PostMapping("/insert")
    public Result<?> insertShipIdleLocation(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipIdleLocationInsertReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        ShipIdleLocation location = new ShipIdleLocation();
        location.setImo(reqBody.getImo());
        location.setPortId(reqBody.getPortId());
        location.setUploaderId(uploaderId);
        return Result.success(shipIdleLocationService.insertShipIdleLocation(location));
    }

    @ApiOperation(value = "List ship idle locations")
    @PostMapping("/list")
    public Result<PageData<ShipIdleLocationVo>> listShipIdleLocations(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipIdleLocationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipIdleLocationService.listShipIdleLocations(reqBody.getPagination(),
                reqBody.getImo(),
                reqBody.getPortId(),
                uploaderId));
    }

    @ApiOperation(value = "Delete ship idle location")
    @DeleteMapping("/delete")
    public Result<?> deleteShipIdleLocation(
            @ApiParam(value = "imo", required = true) Long imo) {
        int result = shipIdleLocationService.deleteShipIdleLocationByImo(imo);
        return Result.success(result);
    }

}

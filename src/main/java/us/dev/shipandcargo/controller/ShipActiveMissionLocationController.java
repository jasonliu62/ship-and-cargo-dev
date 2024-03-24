package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.ShipActiveMissionLocationVo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.ShipActiveMissionLocation;
import us.dev.shipandcargo.request.ShipActiveMissionLocationInsertReqBody;
import us.dev.shipandcargo.request.ShipActiveMissionLocationListReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.ShipActiveMissionLocationService;
import us.dev.shipandcargo.service.UserService;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@Api(tags = {"Ship Active Mission Location Module"})
@RequestMapping(value = "/ship-mission-location")
public class ShipActiveMissionLocationController {

    @Autowired
    private ShipActiveMissionLocationService shipActiveMissionLocationService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add new ship active mission location")
    @PostMapping("/insert")
    public Result<?> insertShipActiveMissionLocation(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipActiveMissionLocationInsertReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        ShipActiveMissionLocation location = new ShipActiveMissionLocation();
        location.setImo(reqBody.getImo());
        location.setLatitude(reqBody.getLatitude());
        location.setLongitude(reqBody.getLongitude());
        location.setPostPortId(reqBody.getPostPortId());
        location.setNextPortId(reqBody.getNextPortId());
        location.setSpeed(reqBody.getSpeed());
        location.setDepartureDistance(reqBody.getDepartureDistance());
        location.setArrivalDistance(reqBody.getArrivalDistance());
        // LocalDateTime tm = reqBody.getCurrentTime();
        location.setCurrentTime(reqBody.getCurrentTime());
        location.setUploaderId(uploaderId);
        return Result.success(shipActiveMissionLocationService.insertShipActiveMissionLocation(location));
    }

    @ApiOperation(value = "List ship active mission locations")
    @PostMapping("/list")
    public Result<PageData<ShipActiveMissionLocationVo>> listShipActiveMissionLocations(
            @Valid
            HttpServletRequest request,
            @RequestBody ShipActiveMissionLocationListReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipActiveMissionLocationService.listShipActiveMissionLocations(reqBody.getPagination(),
                reqBody.getImo(),
                reqBody.getLatitude(),
                reqBody.getLongitude(),
                reqBody.getPostPortId(),
                reqBody.getNextPortId(),
                reqBody.getSpeed(),
                reqBody.getDepartureDistance(),
                reqBody.getArrivalDistance(),
                reqBody.getCurrentTime(),
                uploaderId));
    }

    @ApiOperation(value = "Delete ship active mission location")
    @DeleteMapping("/delete")
    public Result<?> deleteShipActiveMissionLocation(
            @ApiParam(value = "imo", required = true) Long imo) {
        int result = shipActiveMissionLocationService.deleteShipActiveMissionLocationByImo(imo);
        return Result.success(result);
    }

}

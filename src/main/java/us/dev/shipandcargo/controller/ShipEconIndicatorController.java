package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.ShipEconIndicator;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.ShipEconUpdateReqBody;
import us.dev.shipandcargo.service.ShipEconIndicatorService;
import us.dev.shipandcargo.service.ShipService;
import us.dev.shipandcargo.service.UserService;

import java.util.List;

@RestController
@Api(tags = {"Ship Econ Indicator Module"})
@RequestMapping(value = "/ship-econ-indicator")
@CrossOrigin
public class ShipEconIndicatorController {

    @Autowired
    private ShipEconIndicatorService shipEconIndicatorService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "insert Ship Econ and list Ship")
    @PostMapping("/insert-list")
    public Result<?> insertListShip(@Valid HttpServletRequest request,
                                     @ApiParam(value = "imo", required = true) Long imo) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        Ship ship = shipService.selectShipByImoAndUploaderId(imo, uploaderId);
        if (ship == null) {
            throw new ApiException(ApiMessage.SHIP_NOT_EXISTED);
        }
        return Result.success(shipEconIndicatorService.listInsertHandler(ship, imo, uploaderId));

    }

    @ApiOperation(value = "sort-list")
    @PostMapping("/sort-list")
    public Result<?> sortListShip(@Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        List<Ship> shipList = shipEconIndicatorService.selectShipsBySelectorId(uploaderId);
        return Result.success(shipEconIndicatorService.listShipHandler(shipList));
    }

    @ApiOperation(value = "update Ship Econ")
    @PostMapping("/update")
    public Result<?> updateShipEcon(@ApiParam(value = "imo", required = true) Long imo,
                                     @Valid HttpServletRequest request,
                                     @RequestBody ShipEconUpdateReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        ShipEconIndicator shipEconIndicator = shipEconIndicatorService.selectShipEconIndicatorByImoAndSelector(imo, uploaderId);
        shipEconIndicator.setOilConsumption(reqBody.getOilConsumption());
        shipEconIndicator.setChartType(reqBody.getChartType());
        shipEconIndicator.setCharterTime(reqBody.getCharterTime());
        shipEconIndicator.setDailyRent(reqBody.getDailyRent());
        shipEconIndicator.setOilPrice(reqBody.getOilPrice());
        shipEconIndicator.setSpeed(reqBody.getSpeed());
        return Result.success(shipEconIndicatorService.updateShipEconIndicator(shipEconIndicator));
    }


    @ApiOperation(value = "list Ship Econ")
    @PostMapping("/list")
    public Result<?> listShipEcon(@ApiParam(value = "imo", required = true) Long imo,
                                   @Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipEconIndicatorService.listHandler(imo, uploaderId));
    }

    @ApiOperation(value = "delete Ship Econ")
    @DeleteMapping("/delete-single")
    public Result<?> deleteShipEcon(@Valid HttpServletRequest request, @ApiParam(value = "imo", required = true) Long imo) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipEconIndicatorService.deleteShipEconIndicatorByImoAndSelector(imo, uploaderId));
    }

    @ApiOperation(value = "delete all Ship Econ")
    @DeleteMapping("/delete-all")
    public Result<?> deleteAllShipEcon(@Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(shipEconIndicatorService.deleteAllBySelector(uploaderId));
    }

}

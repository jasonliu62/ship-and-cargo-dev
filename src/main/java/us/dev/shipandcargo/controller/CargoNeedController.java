package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.CargoNeedVo;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.CargoNeed;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.request.CargoNeedUpdateReqBody;
import us.dev.shipandcargo.service.CargoNeedService;
import us.dev.shipandcargo.service.CargoService;
import us.dev.shipandcargo.service.UserService;

import java.util.List;

@RestController
@Api(tags = {"Cargo Need Module"})
@RequestMapping(value = "/cargo-need")
@CrossOrigin
public class CargoNeedController {

    @Autowired
    private CargoNeedService cargoNeedService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private UserService userService;

    // 去call cargo里的list然后展示 contractNumber，cargoId，装货港，卸货港
    @ApiOperation(value = "insert cargo need and list cargo")
    @PostMapping("/insert-list")
    public Result<?> insertListCargo(@Valid HttpServletRequest request,
                                               @ApiParam(value = "contractNumber", required = true) Long contractNumber,
                                               @ApiParam(value = "cargoId", required = true) Long cargoId) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();

        Cargo cargo = cargoService.selectCargoById(cargoId);
        if (cargo == null) {
            return Result.failure(ApiMessage.CARGO_NOT_EXIST);
        }
        int status = cargo.getStatus();

        String unloadPort = cargo.getUnloadPortId();
        String loadPort = cargo.getLoadPortId();

        return Result.success(cargoNeedService.insertListHandler(uploaderId, status, unloadPort, loadPort, cargoId, contractNumber));
    }

    @ApiOperation(value = "sort-list")
    @PostMapping("/sort-list")
    public Result<?> sortListCargo(@Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        List<Cargo> cargos = cargoNeedService.selectCargosByUploaderId(uploaderId);
        return Result.success(cargoNeedService.listCargoNeedsHandler(cargos));
    }

    @ApiOperation(value = "update cargo Need")
    @PostMapping("/update")
    public Result<?> updateCargoNeed(@ApiParam(value = "cargoId", required = true) Long cargoId,
                                     @Valid HttpServletRequest request,
                                     @RequestBody CargoNeedUpdateReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        CargoNeed cargoNeed = cargoNeedService.selectCargoNeedByIdAndUploader(cargoId, uploaderId);
        cargoNeed.setLoadCost(reqBody.getLoadCost());
        cargoNeed.setUnloadCost(reqBody.getUnloadCost());
        cargoNeed.setOther(reqBody.getOther());
        cargoNeed.setLoadTime(reqBody.getLoadTime());
        cargoNeed.setUnloadTime(reqBody.getUnloadTime());
        cargoNeed.setAnchorTime(reqBody.getAnchorTime());
        cargoNeed.setIrregularstoppingTime(reqBody.getIrregularstoppingTime());
        cargoNeed.setNavigationTime(reqBody.getNavigationTime());
        cargoNeed.setVoyageTime(reqBody.getVoyageTime());
        return Result.success(cargoNeedService.updateCargoNeed(cargoNeed));
    }

    @ApiOperation(value = "list cargo Need")
    @PostMapping("/list")
    public Result<?> listCargoNeed(@ApiParam(value = "cargoId", required = true) Long cargoId,
                                     @Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(cargoNeedService.listCargoNeedHandler(cargoId, uploaderId));
    }

    @ApiOperation(value = "delete cargo Need")
    @DeleteMapping("/delete-single")
    public Result<?> deleteCargoNeed(@Valid HttpServletRequest request, @ApiParam(value = "cargoId", required = true) Long cargoId) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(cargoNeedService.deleteCargoNeedByIdAndUploader(cargoId, uploaderId));
    }

    @ApiOperation(value = "delete all cargo Need")
    @DeleteMapping("/delete-all")
    public Result<?> deleteAllCargoNeed(@Valid HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(cargoNeedService.deleteCargoNeedByUploaderId(uploaderId));
    }

}

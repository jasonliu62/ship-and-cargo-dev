package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.CargoInsertReqBody;
import us.dev.shipandcargo.request.CargoReqBody;
import us.dev.shipandcargo.service.CargoService;
import us.dev.shipandcargo.util.ResultUtil;

import java.util.List;

@RestController
@Api(tags = {"Cargo Module"})
@RequestMapping(value = "/cargo")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @ApiOperation(value = "insert cargo")
    @PostMapping("/insert")
    public Result<?> insertCargo(@RequestBody CargoInsertReqBody reqBody) {
        Cargo cargo = new Cargo();
        cargo.setContractNumber(reqBody.getContractNumber());
        cargo.setContractType(reqBody.getContractType());
        cargo.setCargoId(reqBody.getCargoId());
        cargo.setCargoVolume(reqBody.getCargoVolume());
        cargo.setCargoType(reqBody.getCargoType());
        cargo.setCargoflowArea(reqBody.getCargoflowArea());
        cargo.setLoadPortId(reqBody.getLoadPortId());
        cargo.setUnloadPortId(reqBody.getUnloadPortId());
        cargo.setLayDay(reqBody.getLayDay());
        cargo.setDischargeDay(reqBody.getDischargeDay());
        cargo.setFreightRate(reqBody.getFreightRate());
        cargo.setVolumeRate(reqBody.getVolumeRate());
        cargo.setLoadportDepth(reqBody.getLoadportDepth());
        cargo.setUnloadportDepth(reqBody.getUnloadportDepth());
        cargo.setVoyageNumber(reqBody.getVoyageNumber());
        cargo.setVoyageVolume(reqBody.getVoyageVolume());
        cargo.setVoyagePeriod(reqBody.getVoyagePeriod());
        cargo.setStatus(reqBody.getStatus());
        return ResultUtil.success(cargoService.insertCargo(cargo));
    }

    @ApiOperation(value = "cargo list")
    @GetMapping("/list")
    public Result<List<Cargo>> listCargo(@RequestBody CargoReqBody cargoReqBody) {
        return ResultUtil.success(cargoService.listCargo(
                cargoReqBody.getPagination(),
                cargoReqBody.getContractNumber(),
                cargoReqBody.getContractType(),
                cargoReqBody.getCargoId(),
                cargoReqBody.getCargoVolume(),
                cargoReqBody.getCargoType(),
                cargoReqBody.getCargoflowArea(),
                cargoReqBody.getLoadPortId(),
                cargoReqBody.getUnloadPortId(),
                cargoReqBody.getLayDay(),
                cargoReqBody.getDischargeDay(),
                cargoReqBody.getFreightRate(),
                cargoReqBody.getVolumeRate(),
                cargoReqBody.getLoadportDepth(),
                cargoReqBody.getUnloadportDepth(),
                cargoReqBody.getVoyageNumber(),
                cargoReqBody.getVoyageVolume(),
                cargoReqBody.getVoyagePeriod(),
                cargoReqBody.getStatus()
        ));
    }

}

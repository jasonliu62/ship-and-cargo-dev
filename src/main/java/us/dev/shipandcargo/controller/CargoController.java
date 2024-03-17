package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.service.CargoService;
import us.dev.shipandcargo.util.ResultUtil;

import java.util.List;

@Controller
@Api(tags = {"Cargo Module"})
@RequestMapping(value = "/cargo")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @ApiOperation(value = "insert cargo")
    @PostMapping("/insert")
    public Result<?> insertCargo(@ApiParam(value = "cargoId") Long cargoId,
                                 @ApiParam(value = "cargoVolume") Long cargoVolume,
                                 @ApiParam(value = "cargoType") String cargoType,
                                 @ApiParam(value = "loadPortId") Long loadPortId,
                                 @ApiParam(value = "unloadPortId") Long unloadPortId,
                                 @ApiParam(value = "layDay") Long layDay,
                                 @ApiParam(value = "company") String company) {
        Cargo cargo = new Cargo();
        cargo.setCargoId(cargoId);
        cargo.setCargoVolume(cargoVolume);
        cargo.setCargoType(cargoType);
        cargo.setLoadPortId(loadPortId);
        cargo.setUnloadPortId(unloadPortId);
        cargo.setLayDay(layDay);
        cargo.setCompany(company);
        return ResultUtil.success(cargoService.insertCargo(cargo));
    }

    @ApiOperation(value = "cargo list")
    @GetMapping("/list")
    public Result<List<Cargo>> listCargo(@ApiParam(value = "Pagination Parameter", required = true) PaginationProps pagination,
                                         @ApiParam(value = "cargoId") Long cargoId,
                                         @ApiParam(value = "cargoVolume") Long cargoVolume,
                                         @ApiParam(value = "cargoType") String cargoType,
                                         @ApiParam(value = "loadPortId") Long loadPortId,
                                         @ApiParam(value = "unloadPortId") Long unloadPortId,
                                         @ApiParam(value = "layDay") Long layDay,
                                         @ApiParam(value = "company") String company
    // 加个几把合同种类
    ) {
        return ResultUtil.success(cargoService.listCargo(pagination,
                cargoId,
                cargoVolume,
                cargoType,
                loadPortId,
                unloadPortId,
                layDay,
                company));
    }

}

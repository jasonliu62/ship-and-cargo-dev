package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.SimulationHistory;
import us.dev.shipandcargo.request.NewAlgorithmReqBody;
import us.dev.shipandcargo.request.PaginationReqBody;
import us.dev.shipandcargo.request.ShipInsertReqBody;
import us.dev.shipandcargo.request.SimulationReqBody;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.service.SimulationService;
import us.dev.shipandcargo.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(tags = {"Simulation Module"})
@RequestMapping(value = "/simulation")
@CrossOrigin // 儿第 记得加这个！
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "insert SimulationHistory")
    @PostMapping("/insert")
    public Result<?> insertSimulationHistory(
            @Valid
            HttpServletRequest request,
            @RequestBody SimulationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        List<Cargo> cargoList = reqBody.getCargoList();
        List<Ship> shipList = reqBody.getShipList();
        LocalDateTime startDay = reqBody.getStartDay();
        LocalDateTime endDay = reqBody.getEndDay();
        return Result.success(simulationService.insertSimulationHistory(shipList, cargoList, startDay, endDay, uploaderId));
    }

    @ApiOperation(value = "Simulation")
    @PostMapping("/simulation")
    public Result<?> simulation(
            @Valid
            HttpServletRequest request,
            @RequestBody SimulationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        List<Cargo> cargoList = reqBody.getCargoList();
        List<Ship> shipList = reqBody.getShipList();
        LocalDateTime startDay = reqBody.getStartDay();
        LocalDateTime endDay = reqBody.getEndDay();
        simulationService.insertSimulationHistory(shipList, cargoList, startDay, endDay, uploaderId);
        NewAlgorithmReqBody newAlgorithmReqBody = new NewAlgorithmReqBody();
        newAlgorithmReqBody.setCargoList(cargoList);
        newAlgorithmReqBody.setGroupId(simulationService.getGroupId());
        newAlgorithmReqBody.setShipList(shipList);
        newAlgorithmReqBody.setIndicatorList(reqBody.getIndicatorList());
        newAlgorithmReqBody.setShipManagementList(reqBody.getShipManagementList());
        newAlgorithmReqBody.setCargoNeedList(reqBody.getCargoNeedList());
        newAlgorithmReqBody.setUploaderId(uploaderId);

        String url = "http://106.55.105.246:3030/algorithm/calculation";
        Result<?> response = restTemplate.postForObject(url, newAlgorithmReqBody, Result.class);

        return Result.success(response);
    }

    @ApiOperation(value = "list SimulationHistory")
    @PostMapping("/list-simulation-history")
    public Result<?> listHistory(
            @Valid
            HttpServletRequest request,
            @RequestBody PaginationReqBody reqBody) {
        // pagination格式详见比如：shipReqBody
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(simulationService.listSimulationHistory(reqBody.getPagination(), uploaderId));
    }

    // 还要写list出来每次具体跑的东西。。
}

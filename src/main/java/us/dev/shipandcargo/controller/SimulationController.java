package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import us.dev.shipandcargo.domain.*;
import us.dev.shipandcargo.request.*;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private OutputService outputService;

    @Autowired
    private OutputEachService outputEachService;

    @Autowired
    private CargoNeedService cargoNeedService;

    @Autowired
    private ShipEconIndicatorService indicatorService;


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

    // 这边要写个把simulation之前每个list都提取出来的api。。。

    @ApiOperation(value = "Simulation")
    @PostMapping("/simulation")
    @Deprecated
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

        String url = "http://localhost:3030/algorithm/calculation";
        Result<?> response = restTemplate.postForObject(url, newAlgorithmReqBody, Result.class);

        return Result.success(response);
    }

    @ApiOperation(value = "Final Simulation")
    @PostMapping("/final-simulation")
    public Result<?> finalSimulation(
            @Valid
            HttpServletRequest request,
            @RequestBody FinalSimulationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        List<CargoNeed> cargoNeedList = cargoNeedService.selectAllCargoNeedsByUploaderSortedByLayDay(uploaderId);
        List<ShipEconIndicator> shipEconIndicatorList = indicatorService.selectAllShipEconIndicatorsBySelector(uploaderId);

        List<Ship> shipList = new ArrayList<>();
        List<ShipManagement> shipManagementList = new ArrayList<>();
        List<Cargo> cargoList = new ArrayList<>();

        simulationService.listBuildHandler(shipEconIndicatorList, shipList, shipManagementList, cargoNeedList, cargoList);

        LocalDateTime startDay = reqBody.getStartDay();
        LocalDateTime endDay = reqBody.getEndDay();
        simulationService.insertSimulationHistory(shipList, cargoList, startDay, endDay, uploaderId);
        NewAlgorithmReqBody newAlgorithmReqBody = new NewAlgorithmReqBody();
        newAlgorithmReqBody.setCargoList(cargoList);
        newAlgorithmReqBody.setGroupId(simulationService.getGroupId());
        newAlgorithmReqBody.setShipList(shipList);
        newAlgorithmReqBody.setIndicatorList(shipEconIndicatorList);
        newAlgorithmReqBody.setShipManagementList(shipManagementList);
        newAlgorithmReqBody.setCargoNeedList(cargoNeedList);
        newAlgorithmReqBody.setUploaderId(uploaderId);

        String url = "http://localhost:3030/algorithm/calculation";
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

    @ApiOperation(value = "list Output")
    @PostMapping("/list-output")
    public Result<?> listOutput(
            @Valid
            HttpServletRequest request,
            @ApiParam Long groupId,
            @RequestBody PaginationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(outputService.listOutput(reqBody.getPagination(), groupId, uploaderId));
    }


    // OutputId 就是上一个Output的id
    // 点击详情加载出来OutputEach
    @ApiOperation(value = "list OutputEach")
    @PostMapping("/list-output-each")
    public Result<?> listOutputEach(
            @Valid
            HttpServletRequest request,
            @ApiParam Long groupId,
            @ApiParam Long outputId,
            @RequestBody PaginationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        return Result.success(outputEachService.listOutputEach(reqBody.getPagination(), groupId, outputId, uploaderId));
    }

    @ApiOperation(value = "delete 3 Outputs")
    // 把OutputHistory, Output们, OutputEach们一起删了
    @DeleteMapping("/delete-from-output-history")
    public Result<?> delete3Outputs(
            @Valid
            HttpServletRequest request,
            @ApiParam Long groupId,
            @RequestBody PaginationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        int res0 = simulationService.deleteSimulationHistory(groupId, uploaderId);
        outputService.deleteFromHistory(groupId, uploaderId);
        outputEachService.deleteFromHistory(groupId, uploaderId);
        return Result.success(res0);
    }

    @ApiOperation(value = "delete 2 Outputs")
    // 把Output, OutputEach们一起删了
    @DeleteMapping("/delete-from-output")
    public Result<?> delete2Outputs(
            @Valid
            HttpServletRequest request,
            @ApiParam Long groupId,
            @ApiParam Long id,
            @RequestBody PaginationReqBody reqBody) {
        String token = request.getHeader("Authorization").substring(7);
        Long uploaderId = userService.findUserByToken(token).getId();
        int res = outputService.deleteOutputBy3Ids(groupId, id, uploaderId);
        outputEachService.deleteOutputEachsBy3Ids(groupId, id, uploaderId);
        return Result.success(res);
    }


}

package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.PortOtherVo;
import us.dev.shipandcargo.domain.PortOther;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.PortOtherInsertReqBody;
import us.dev.shipandcargo.request.PortOtherReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.PortOtherService;
import us.dev.shipandcargo.util.ResultUtil;

@RestController
@Api(tags = {"Port Other Module"})
@RequestMapping(value = "/port-other")
@CrossOrigin
public class PortOtherController {

    @Autowired
    private PortOtherService portOtherService;

    @ApiOperation(value = "insert Port Other")
    @PostMapping("/insert")
    public Result<?> insertPortOther(@RequestBody PortOtherInsertReqBody reqBody) {
        PortOther portOther = new PortOther();
        portOther.setPortId(reqBody.getPortId());
        portOther.setDistance(reqBody.getDistance());
        portOther.setOilPrice(reqBody.getOilPrice());
        portOther.setAvgTimeStay(reqBody.getAvgTimeStay());
        portOther.setLoadTime(reqBody.getLoadTime());
        portOther.setLoadEfficiency(reqBody.getLoadEfficiency());
        portOther.setUnloadTime(reqBody.getUnloadTime());
        portOther.setUnloadEfficiency(reqBody.getUnloadEfficiency());
        portOther.setPortFee(reqBody.getPortFee());
        portOther.setAvgAnchorTime(reqBody.getAvgAnchorTime());
        return Result.success(portOtherService.insertPortOther(portOther));
    }

    @ApiOperation(value = "list Port Other")
    @PostMapping("/list")
    public Result<PageData<PortOtherVo>> listPortOther(@RequestBody PortOtherReqBody reqBody) {
        return ResultUtil.success(portOtherService.listPortOther(reqBody.getPagination(),
                reqBody.getPortId(),
                reqBody.getDistance(),
                reqBody.getOilPrice(),
                reqBody.getAvgTimeStay(),
                reqBody.getLoadTime(),
                reqBody.getLoadEfficiency(),
                reqBody.getUnloadTime(),
                reqBody.getUnloadEfficiency(),
                reqBody.getPortFee(),
                reqBody.getAvgAnchorTime()));
    }

    @ApiOperation(value = "delete port other")
    @DeleteMapping("/delete")
    public Result<?> deletePortOther(@ApiParam(value = "portId", required = true) Long portId) {
        return Result.success(portOtherService.deletePortOtherById(portId));
    }


}

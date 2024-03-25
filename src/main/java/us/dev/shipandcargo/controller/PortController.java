package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.PortVo;
import us.dev.shipandcargo.domain.Port;
import us.dev.shipandcargo.domain.PortActivity;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.PortInsertReqBody;
import us.dev.shipandcargo.request.PortReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.PortService;
import us.dev.shipandcargo.util.ResultUtil;

@RestController
@Api(tags = {"Port Module"})
@RequestMapping(value = "/port")
@CrossOrigin
public class PortController {

    @Autowired
    private PortService portService;

    @ApiOperation(value = "insert port")
    @PostMapping("/insert")
    public Result<?> insertPort(@RequestBody PortInsertReqBody reqBody) {
        Port port = new Port();
        port.setPortId(reqBody.getPortId());
        port.setNameCHN(reqBody.getNameCHN());
        port.setNameENG(reqBody.getNameENG());
        port.setLatitude(reqBody.getLatitude());
        port.setLongitude(reqBody.getLongitude());
        port.setMinDraft(reqBody.getMinDraft());
        return ResultUtil.success(portService.insertPort(port));
    }

    @ApiOperation(value = "port list")
    @PostMapping ("/list")
    public Result<PageData<PortVo>> listPort(@RequestBody PortReqBody portReqBody) {
        return ResultUtil.success(portService.listPort(
                portReqBody.getPagination(), portReqBody.getPortId(),
                portReqBody.getNameCHN(), portReqBody.getNameENG(),
                portReqBody.getLatitude(), portReqBody.getLongitude(),
                portReqBody.getMinDraft()
        ));


    }

    @ApiOperation(value = "Delete port activity")
    @DeleteMapping("/delete")
    public Result<?> deletePortActivity(
            @ApiParam(value = "portId", required = true) Long portId) {
        int result = portService.deletePortByPortId(portId);
        return Result.success(result);
    }

}

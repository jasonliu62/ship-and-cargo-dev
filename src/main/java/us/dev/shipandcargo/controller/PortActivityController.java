package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.Vo.PortActivityVo;
import us.dev.shipandcargo.domain.PortActivity;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.PortActivityInsertReqBody;
import us.dev.shipandcargo.request.PortActivityReqBody;
import us.dev.shipandcargo.request.PortInsertReqBody;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.service.PortActivityService;
import us.dev.shipandcargo.util.ResultUtil;

@RestController
@Api(tags = {"Port Activity Module"})
@RequestMapping(value = "/portActivity")
@CrossOrigin
public class PortActivityController {

    @Autowired
    private PortActivityService portActivityService;

    @ApiOperation(value = "insert port activity")
    @PostMapping("/insert")
    public Result<?> insertPortActivity(@RequestBody PortActivityInsertReqBody reqBody) {
        PortActivity portActivity = new PortActivity();
        portActivity.setPortId(reqBody.getPortId());
        portActivity.setShipAmount(reqBody.getShipAmount());
        return ResultUtil.success(portActivityService.insertPortActivity(portActivity));
    }

    @ApiOperation(value = "port activity list")
    @PostMapping ("/list")
    public Result<PageData<PortActivityVo>> listPortActivity(@RequestBody PortActivityReqBody portActivityReqBody) {
        return ResultUtil.success(portActivityService.listPortActivity(
                portActivityReqBody.getPagination(), portActivityReqBody.getPortId(),
                portActivityReqBody.getShipAmount()
        ));
    }

}

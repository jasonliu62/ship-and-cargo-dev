package us.dev.shipandcargo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.dev.shipandcargo.domain.Result;
import us.dev.shipandcargo.request.LoginReqBody;
import us.dev.shipandcargo.request.RegisterReqBody;
import us.dev.shipandcargo.service.UserService;
import us.dev.shipandcargo.util.ResultUtil;

@RestController
@Api(tags = {"User Module"})
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "user register")
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterReqBody reqBody) {
        String password = reqBody.getPassword();
        String email = reqBody.getEmail();
        String name = reqBody.getName();
        return ResultUtil.success(userService.registerUser(email, password, name));
    }

    @ApiOperation(value = "user login")
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginReqBody reqBody) {
        String password = reqBody.getPassword();
        String email = reqBody.getEmail();
        return ResultUtil.success(userService.login(email, password));
    }

    @ApiOperation(value = "delete user by email")
    @PostMapping("/delete-by-email")
    public Result<?> delete(@RequestParam String email) {
        return ResultUtil.success(userService.deleteUserByEmail(email));
    }


}

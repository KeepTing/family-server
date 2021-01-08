package cn.keepting.family.server.controller;

import cn.keepting.family.server.constant.BaseResponse;
import cn.keepting.family.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.controller
 * @date:2021/1/7
 **/
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse login() {
        return BaseResponse.ok();
    }
}

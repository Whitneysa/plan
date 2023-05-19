package com.zip.controller;


import com.zip.pojo.dto.LoginDTO;
import com.zip.pojo.dto.MessageLoginDTO;
import com.zip.pojo.dto.RegisterDTO;
import com.zip.pojo.vo.LoginVO;
import com.zip.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import response.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@Api
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

//    @Resource
//    private ThirdPartyClient thirdPartyClient;

//    @GetMapping("code")
//    public Result<String> getCode(HttpServletResponse response) throws IOException {
//        long currentTimeMillis = System.currentTimeMillis();
//        System.out.println("start:" + currentTimeMillis);
//        thirdPartyClient.codeLogin("17359403667","1123");
//        System.out.println("服务用时:" + (System.currentTimeMillis() - currentTimeMillis));
//        return loginService.getCaptcha(response);
//    }

    @GetMapping("/getCaptcha")
    @ApiOperation("获取验证码")
    public Result<String> getCaptcha(HttpServletResponse response) throws IOException {
        return loginService.getCaptcha(response);
    }

    @GetMapping("/sendMessage")
    @ApiOperation("发送短信")
    public Result sendMessage(@RequestParam("phone") String phone) {
        return loginService.sendMessage(phone);
    }


    @GetMapping("/checkUserName")
    @ApiOperation("校验用户名")
    public Result checkUserName(@NotBlank String username){
        return loginService.checkUserName(username);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@Valid @RequestBody RegisterDTO registerDTO){
        return loginService.register(registerDTO);
    }

    @PostMapping("/login")
    @ApiOperation("用户账号登录")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

    @ApiOperation("用户验证码登录")
    @PostMapping("/messageLogin")
    public Result<LoginVO> messageLogin(@Valid @RequestBody MessageLoginDTO messageLoginDTO){
       return loginService.messageLogin(messageLoginDTO);
    }
}

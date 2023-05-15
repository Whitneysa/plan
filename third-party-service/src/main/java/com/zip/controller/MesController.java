package com.zip.controller;

import com.zip.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.RespResult;
import response.Result;
import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "阿里云短信服务")
@RestController
@RequestMapping("/api/msm")
public class MesController {

//    @Resource
//    private MsmService msmService;
//
//    @ApiOperation(value = "发送短信验证码")
//    @GetMapping(value = "/send/{phone}/{code}")
//    public Result codeLogin(@PathVariable String phone,  @PathVariable String code){
//        HashMap<String, String> map = new HashMap<>();
//        map.put("code",code);
//        msmService.send(map,phone);
//        return RespResult.success();
//    }
}

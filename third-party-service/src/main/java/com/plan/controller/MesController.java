package com.plan.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

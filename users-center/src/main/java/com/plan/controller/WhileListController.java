package com.plan.controller;

import com.plan.service.WhileListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import response.RespResult;
import response.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gateway/whileList")
@Api("网关白名单")
public class WhileListController {

    @Resource
    private WhileListService whileService;

    @ApiOperation("新增白名单")
    @PostMapping("/add")
    private Result addWhileList(){

        return RespResult.success();
    }

    @ApiOperation("批量添加白名单")
    @PostMapping("/addBatch")
    private Result addBatchWhiteList(){

        return RespResult.success();
    }


    @ApiOperation("删除白名单")
    @DeleteMapping("/delete")
    private Result deleteWhileList(){

        return RespResult.success();
    }

    @ApiOperation("编辑白名单")
    @PostMapping("/edit")
    private Result editWhileList(){

        return RespResult.success();
    }

    @ApiOperation("查询白名单")
    @GetMapping("/query")
    private Result queryWhileList(){

        return RespResult.success();
    }

}

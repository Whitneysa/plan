package com.it.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pojo.gateway.WhiteList;
import response.Result;
import java.util.List;

@FeignClient(name = "users-center")
public interface UserCenterClient {

    @GetMapping(value = "/gateway/whiteList/getAllWhiteList")
    public Result<List<WhiteList>> getAllWhiteList();
}

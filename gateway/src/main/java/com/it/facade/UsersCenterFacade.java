package com.it.facade;

import com.it.client.UserCenterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pojo.gateway.WhiteList;
import response.Result;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class UsersCenterFacade {

    @Resource
    private UserCenterClient userCenterClient;

    public List<WhiteList> getAllWhiteList(){
        log.info("远程调用users-center...........");
        long startTime = System.currentTimeMillis();
        Result<List<WhiteList>> allWhiteList = userCenterClient.getAllWhiteList();
        log.info("调用客户中心用时:{}", System.currentTimeMillis() - startTime);
        return allWhiteList.getT();
    }
}

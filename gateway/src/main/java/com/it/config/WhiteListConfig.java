package com.it.config;

import com.it.dao.WhiteListDao;
import constants.ConstantRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import pojo.gateway.WhiteList;
import javax.annotation.Resource;
import java.util.List;

@Configuration
@Slf4j
public class WhiteListConfig {

    @Resource
    private WhiteListDao whiteListDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private void initWhiteList(){
        List<WhiteList> whiteLists = whiteListDao.getAll();
        whiteLists.forEach(whiteList -> {
            redisTemplate.opsForSet().add(ConstantRedisKey.PATH_WHITE_SET, whiteList);
        });
        log.info("初始化白名单成功...............");
    }
}
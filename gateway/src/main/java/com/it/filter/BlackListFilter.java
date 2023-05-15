package com.it.filter;

import constants.ConstantRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pojo.user.User;
import reactor.core.publisher.Mono;
import utils.UserInfoHolder;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class BlackListFilter implements GlobalFilter, Ordered {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 黑名单
     * 定义:10秒访问20次以上加入黑名单
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取用户ip
        XForwardedRemoteAddressResolver resolver = XForwardedRemoteAddressResolver.maxTrustedIndex(1);
        InetSocketAddress inetSocketAddress = resolver.resolve(exchange);
        String hostAddress = inetSocketAddress.getAddress().getHostAddress();

        //ip黑名单
        Boolean ipExistFlag = stringRedisTemplate.opsForSet().isMember(ConstantRedisKey.IP_BLACKLIST_SET, hostAddress);
        if (ipExistFlag){
            return exchange.getResponse().setComplete();
        }

        //用户黑名单
        User userInfo = UserInfoHolder.getUserInfo();
        Boolean userExistFlag = stringRedisTemplate.opsForSet()
                .isMember(ConstantRedisKey.USER_BLACKLIST_SET, userInfo.getUserId());
        if (userExistFlag){
            return exchange.getResponse().setComplete();
        }

        //ip访问次数统计


        //用户访问次数统计




        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

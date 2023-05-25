package com.it.filter;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import constants.ConstantRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pojo.user.User;
import reactor.core.publisher.Mono;
import utils.JwtUtil;
import utils.UserInfoHolder;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 认证
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getQueryParams().getFirst("authorization");
        String token = org.apache.logging.log4j.util.Strings.EMPTY;

        //解析jwt
        try {
            token = JwtUtil.parseJWT(authorization).getSubject();
        } catch (Exception e) {
            //获取用户ip地址
            XForwardedRemoteAddressResolver resolver = XForwardedRemoteAddressResolver.maxTrustedIndex(1);
            InetSocketAddress inetSocketAddress = resolver.resolve(exchange);
            String hostAddress = inetSocketAddress.getAddress().getHostAddress();
            log.error("ip:{}用户:认证失败", hostAddress);
            return exchange.getResponse().setComplete();
        }

        //获取用户id,判断用户角色是否登录(是否再缓存中)
        String jsonUser = stringRedisTemplate.opsForValue().get(ConstantRedisKey.USER_IDENTITY_AUTH + token);
        if (Strings.isNullOrEmpty(jsonUser)){
            return exchange.getResponse().setComplete();
        }

        User user = JSONObject.parseObject(jsonUser, User.class);
        UserInfoHolder.setUserInfo(user);

        return chain.filter(exchange);
    }

    //加载过滤器的顺序，数字越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}

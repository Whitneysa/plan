package com.zip.consumer;

import cn.hutool.core.map.MapUtil;
import com.zip.service.MsmService;
import constants.RocketMqCommon;
import message.MessageMes;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
@RocketMQMessageListener(consumerGroup = RocketMqCommon.PLAN_DEV_GROUP, topic = RocketMqCommon.USER_MESSAGE_LOGIN_TOPIC)
public class MessageConsumer implements RocketMQListener<MessageMes> {

    @Resource
    private MsmService msmService;

    @Override
    public void onMessage(MessageMes messageMes) {
        HashMap<String, String> map = MapUtil.newHashMap();
        map.put("code",messageMes.getCaptcha().toString());
        msmService.send(map,messageMes.getPhone());
    }
}

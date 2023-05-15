package com.zip.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zip.service.MsmService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
@Slf4j
public class MsmServiceImpl implements MsmService {
    /**
     * 发送验证码
     * @param param  验证码
     * @param phone  手机号
     * @return
     */
    @Override
    public boolean send(Map<String, String> param, String phone) {

        if (Strings.isNullOrEmpty(phone)) {
            return false;
        }

        //default 地域节点，默认就好  后面是 阿里云的 id和秘钥（这里记得去阿里云复制自己的id和秘钥哦）
        DefaultProfile profile = DefaultProfile.getProfile("default"
                , "LTAI5tLtW9HCsaFhm7MvNLq1"
                , "hRmYxfNWdptD6qhRSI67GOxb2ofe3G");
        IAcsClient client = new DefaultAcsClient(profile);

        //这里不能修改
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);                    //手机号
        request.putQueryParameter("SignName", "plan");    //申请阿里云 签名名称（暂时用阿里云测试的，自己还不能注册签名）
        request.putQueryParameter("TemplateCode", "SMS_460780549"); //申请阿里云 模板code（用的也是阿里云测试的）
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("验证码为:{}", response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            log.error("短信验证码发送失败");
            e.printStackTrace();
        }
        return false;
    }
}

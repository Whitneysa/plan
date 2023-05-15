package com.zip.service;

import java.util.Map;

public interface MsmService {
    //发送验证码
    boolean send(Map<String, String> param, String phone);
}

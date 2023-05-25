package com.zip.dao;

import pojo.user.UserInfo;

public interface UserInfoDao {
    UserInfo getByUserId(String userId);
}

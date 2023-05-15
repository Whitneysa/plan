package com.zip.dao;

import pojo.user.User;
import pojo.user.UserInfo;

public interface UserDao {

    User getUserByUsername(String username);

    boolean save(User user);

    boolean saveUserInfo(UserInfo userInfo);

    User getUserByPhone(String phone);
}

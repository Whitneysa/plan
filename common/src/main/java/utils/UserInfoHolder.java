package utils;

import pojo.user.User;


public class UserInfoHolder {
    private ThreadLocal<User> userInfo = new ThreadLocal<User>();

}

package utils;

import org.springframework.core.NamedThreadLocal;
import pojo.user.User;

import javax.annotation.Nullable;


public class UserInfoHolder {

    private static final ThreadLocal<User> USER_INFO_THREAD_LOCAL =
            new NamedThreadLocal<>("UserInfoHolder");

    public static void  reset(){
        USER_INFO_THREAD_LOCAL.remove();
    }

    public static User getUserInfo(){
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static void setUserInfo(@Nullable User user){
        if (user == null){
            reset();
        }else {
            USER_INFO_THREAD_LOCAL.set(user);
        }
    }
}

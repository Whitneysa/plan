package constants;

public interface ConstantRedisKey {
    /**
     * 用户注册登录验证码
     */
    String USER_LOGIN_AND_REGISTER_CAPTCHA = "user:login:captcha:";
    Integer LOGIN_CAPTCHA_EXPIRE_TIME = 60;

    /**
     * 短信登录
     */
    String USER_MESSAGE_LOGIN_CODE = "user:message:login:code";

}

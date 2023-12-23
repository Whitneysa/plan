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
    String USER_MESSAGE_LOGIN_CODE = "user:message:login:code:";

    /**
     * 用户身份认证
     */
    String USER_IDENTITY_AUTH = "user:identity:auth:";

    /**
     * ip黑名单
     */
    String IP_BLACKLIST_SET = "ip:blacklist:set:";

    /**
     * 用户黑名单
     */
    String USER_BLACKLIST_SET = "user:blacklist:set:";

    /**
     * 白名单
     */
    String PATH_WHITE_SET = "path:white:set:";

}

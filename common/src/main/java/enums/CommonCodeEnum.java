package enums;
import lombok.Getter;

@Getter
public enum CommonCodeEnum {

    /**
     * 通用
     */
    SUCCESS(20000,"成功"),
    SYSTEM_ERROR_FAIL(40000,"系统繁忙,请稍后重试!"),

    /**
     * 用户
     */
    USER_NOT_FIND_ERROR(2000001,"该用户不存在"),
    USERNAME_EXISTED(2000002,"用户名已存在,请重新输入"),
    CAPTCHA_INVALID(2000003,"验证码无效，已过期或不正确"),
    USERNAME_OR_PASSWORD_ERROR(2000004,"用户名或密码不正确，请重新输入"),
    USER_PHONE_NOT_FIND(2000005,"该手机号码未注册,请重新输入手机号"),
    PHONE_FORMAT_ERROR(2000006,"手机格式不正确,请重新输入")

    ;


    private Integer Code;
    private String message;
    private CommonCodeEnum(Integer code, String message){
        this.Code = code;
        this.message = message;
    }
}

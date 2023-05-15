package message;
import lombok.Data;

@Data
public class MessageMes {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码
     */
    private Integer captcha;
}

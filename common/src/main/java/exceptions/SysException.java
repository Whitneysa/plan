package exceptions;

import enums.CommonCodeEnum;
import lombok.Getter;

@Getter
public class SysException extends RuntimeException{
    private CommonCodeEnum commonCodeEnum;

    private Integer code;

    private String message;

    public SysException(CommonCodeEnum commonCodeEnum) {
        this.commonCodeEnum = commonCodeEnum;
        this.code = commonCodeEnum.getCode();
        this.message = commonCodeEnum.getMessage();
    }

    public SysException(String message, CommonCodeEnum commonCodeEnum) {
        super(message);
        this.commonCodeEnum = commonCodeEnum;
        this.code = commonCodeEnum.getCode();
        this.message = commonCodeEnum.getMessage();
    }
}

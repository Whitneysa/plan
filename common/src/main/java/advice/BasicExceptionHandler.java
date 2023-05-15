package advice;

import enums.CommonCodeEnum;
import exceptions.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import response.RespResult;
import response.Result;


@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = SysException.class)
    public Result<String> errorHandler(IllegalArgumentException exception) {
        log.error(exception.getMessage());
        return RespResult.fail(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<CommonCodeEnum> errorHandler(Exception exception) {
        log.error(exception.getMessage());
        return RespResult.fail(CommonCodeEnum.SYSTEM_ERROR_FAIL);
    }

    @ResponseBody
    @ExceptionHandler(value = SysException.class)
    public Result<CommonCodeEnum> errorHandler(SysException exception) {
        log.error(exception.getMessage());
        return RespResult.fail(exception.getCommonCodeEnum());
    }


}

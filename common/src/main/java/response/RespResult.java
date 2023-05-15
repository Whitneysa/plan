package response;


import enums.CommonCodeEnum;

public class RespResult {

    public static Result success(){
        Result result = new Result();
        result.setCode(CommonCodeEnum.SUCCESS.getCode());
        result.setMessage(CommonCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(CommonCodeEnum.SUCCESS.getCode());
        result.setMessage(CommonCodeEnum.SUCCESS.getMessage());
        result.setT(obj);
        return result;
    }

    public static Result fail(CommonCodeEnum commonCodeEnum){
        Result result = new Result();
        result.setCode(commonCodeEnum.getCode());
        result.setMessage(commonCodeEnum.getMessage());
        return result;
    }

    public static Result fail(String message){
        Result result = new Result();
        result.setCode(4000000);
        result.setMessage(message);
        return result;
    }
}

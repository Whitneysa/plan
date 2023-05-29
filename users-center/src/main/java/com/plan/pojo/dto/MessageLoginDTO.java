package com.plan.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Valid
@ApiModel
public class MessageLoginDTO {

    @NotBlank
    @Pattern(regexp = "[1]([3-9])[0-9]{9}$")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$")
    @ApiModelProperty("验证码")
    private String captcha;


}

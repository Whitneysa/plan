package com.zip.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
@ApiModel
public class RegisterDTO {

    @NotBlank
    @Length(min = 3,max = 20)
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank
    @Length(min = 6,max = 20)
    @ApiModelProperty("密码")
    private String password;

    @NotBlank
    @ApiModelProperty("验证码的key")
    private String uuid;

    @NotBlank
    @ApiModelProperty("验证码")
    private String code;
}

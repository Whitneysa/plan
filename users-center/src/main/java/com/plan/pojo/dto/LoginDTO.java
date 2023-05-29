package com.plan.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
@ApiModel
public class LoginDTO {
    @NotBlank
    @Length(min = 3,max = 20)
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank
    @Length(min = 6,max = 20)
    @ApiModelProperty("密码")
    private String password;

    @NotBlank
    @ApiModelProperty("验证码")
    private String code;

    @NotEmpty
    @Min(0)
    @Max(1)
    @ApiModelProperty("是否记住密码:0不记住 1记住")
    private Integer remember;
}

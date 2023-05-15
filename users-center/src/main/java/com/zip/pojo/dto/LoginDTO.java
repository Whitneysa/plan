package com.zip.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class LoginDTO {
    @NotBlank
    @Length(min = 3,max = 20)
    private String username;

    @NotBlank
    @Length(min = 6,max = 20)
    private String password;

    @NotBlank
    private String code;

    @NotEmpty
    @Min(0)
    @Max(1)
    private Integer remember;
}

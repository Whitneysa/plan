package com.zip.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class RegisterDTO {

    @NotBlank
    @Length(min = 3,max = 20)
    private String username;

    @NotBlank
    @Length(min = 6,max = 20)
    private String password;

    @NotBlank
    private String uuid;

    @NotBlank
    private String code;
}

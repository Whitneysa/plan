package com.zip.service;
import com.zip.pojo.dto.LoginDTO;
import com.zip.pojo.dto.MessageLoginDTO;
import com.zip.pojo.dto.RegisterDTO;
import com.zip.pojo.vo.LoginVO;
import response.Result;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {

    Result<String> getCaptcha(HttpServletResponse response) throws IOException;

    Result register(RegisterDTO registerDTO);

    Result checkUserName(String username);

    Result<LoginVO> login(LoginDTO loginDTO);

    Result<LoginVO> messageLogin(MessageLoginDTO messageLoginDTO);

    Result sendMessage(String phone);
}

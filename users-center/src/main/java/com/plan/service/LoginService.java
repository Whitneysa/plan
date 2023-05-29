package com.plan.service;
import com.plan.pojo.dto.LoginDTO;
import com.plan.pojo.dto.MessageLoginDTO;
import com.plan.pojo.dto.RegisterDTO;
import com.plan.pojo.vo.LoginVO;
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

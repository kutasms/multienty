package com.chia.multienty.core.tools;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.util.WebUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();

        Result<String> result = null;
        if(authException instanceof BadCredentialsException) {
            result = new Result<>(HttpResultEnum.ILLEGAL_TOKEN);
        } else if(authException instanceof InsufficientAuthenticationException) {
            result = new Result<>(HttpResultEnum.USER_NOT_LOGIN);
        } else {
            result = new Result<>(HttpResultEnum.TOKEN_APPROVE_ERROR);
        }

        // 处理前端
        WebUtil.writeRsp(response, result);
    }
}

package com.tanwei.spring.security.security.exception;

import com.alibaba.fastjson.JSON;
import com.tanwei.spring.security.utils.ApiResult;
import com.tanwei.spring.security.utils.StatusCode;
import com.tanwei.spring.security.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * 认证异常捕获
 *
 * @author tanwei
 * @date 2023-07-13 8:20
 **/
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ApiResult<Void> apiResult = ApiResult.build(StatusCode.UNAUTHORIZED.getCode(),
                StatusCode.UNAUTHORIZED.getMessage(), false, null);
        String responseStr = JSON.toJSONString(apiResult);
        WebUtil.renderString(response, responseStr);
    }
}

package com.tanwei.spring.security.security.exception;

import com.alibaba.fastjson.JSON;
import com.tanwei.spring.security.utils.ApiResult;
import com.tanwei.spring.security.utils.StatusCode;
import com.tanwei.spring.security.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


/**
 * 权限异常捕获
 *
 * @author tanwei
 * @date 2023-07-13 8:21
 **/
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ApiResult<Void> apiResult = ApiResult.build(StatusCode.FORBIDDEN.getCode(),
                StatusCode.FORBIDDEN.getMessage(), false, null);
        String responseStr = JSON.toJSONString(apiResult);
        WebUtil.renderString(response, responseStr);

    }
}

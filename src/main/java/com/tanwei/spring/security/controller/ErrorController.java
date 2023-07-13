package com.tanwei.spring.security.controller;

import com.tanwei.spring.security.utils.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-07-13 9:37
 **/
@RestController
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * 默认路径/error，可以通过server.error.path配置
     */
    @GetMapping(("${server.error.path:/error}"))
    public ApiResult<Void> error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(200); // 强制返回前端状态码为200
        return ApiResult.build(String.valueOf(status.value()), status.getReasonPhrase(), false, null);
    }
}

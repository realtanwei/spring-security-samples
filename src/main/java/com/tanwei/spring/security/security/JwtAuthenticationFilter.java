package com.tanwei.spring.security.security;

import com.tanwei.spring.security.exception.BusinessRuntimeException;
import com.tanwei.spring.security.service.SysUserService;
import com.tanwei.spring.security.utils.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author tanwei
 * @date 2023-07-04 15:35
 **/
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SysUserService sysUserService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(JwtUtil.TOKEN_HEADER);
        if(StringUtils.isBlank(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String username;
        try {
            username = jwtUtil.extractUsername(token);
        } catch (MalformedJwtException exception) {
            throw new BusinessRuntimeException("登录凭证错误或无效!");
        }

        if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = sysUserService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    // 用户凭证
                    null,
                    userDetails.getAuthorities());
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}

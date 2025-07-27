package com.erling.service.handle;

import com.erling.utils.jwt.JwtUtils;
import com.erling.utils.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Optional;
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler
    ) throws Exception {

        // 排除登录/注册等无需验证的接口
        if (request.getRequestURI().contains("/user/api/login") ||
                request.getRequestURI().contains("/user/api/register"))
        {
            return true;
        }

        // 安全获取Cookie
        Cookie[] cookies = request.getCookies();
        String token = Optional.ofNullable(cookies) // 过滤空数组
                .map(Arrays::stream)                // 转换为Stream流
                .flatMap(stream ->  // 过滤掉非jwt_token的cookie
                        stream.filter(c -> "jwt_token".
                                equals(c.getName())
                                ).
                                findFirst())
                .map(Cookie::getValue)                   // 获取token值
                .orElse(null);                     // 若无token，则返回null

        if (token != null && JwtUtils.isTokenExpired(token)) {
            return true;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        objectMapper.writeValue(
                response.getWriter(),
                new Result<>(401, "身份验证失败", null)
        );
        return false;
    }
}

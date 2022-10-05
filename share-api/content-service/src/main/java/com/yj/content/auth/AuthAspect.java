package com.yj.content.auth;

import com.yj.content.utils.JwtOperator;
import com.yj.content.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author yj
 * @date 2022/10/4 14:00
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthAspect {
    private final JwtOperator jwtOperator;

    private final RedisUtil redis;

    @Around("@annotation(com.yj.content.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
        checkToken();
        return point.proceed();
    }

    @Around("@annotation(com.yj.content.auth.CheckAuthorization)")
    public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable {
        try {
            checkToken();
            HttpServletRequest request = getHttpServletRequest();
            String role = (String) request.getAttribute("role");
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            CheckAuthorization annotation = method.getAnnotation(CheckAuthorization.class);
            String value = annotation.value();
            if (!Objects.equals(role, value)) {
                throw new SecurityException("用户无权访问！");
            }
        } catch (Throwable throwable) {
            throw new SecurityException("用户无权访问！", throwable);
        }
        return point.proceed();
    }

    private void checkToken() {
        try {
            HttpServletRequest request = getHttpServletRequest();


            String token = request.getHeader("X-Token");

            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                throw new SecurityException("Token不合法！");
            }

            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("id", claims.get("id"));
            request.setAttribute("nickname", claims.get("nickname"));
            request.setAttribute("role", claims.get("role"));

            String redisToken = redis.get("authUser: " + claims.get("id").toString());

            if (!token.equals(redisToken)) {
                throw new SecurityException("Token 认证错误");
            }
        } catch (Exception e) {
            throw new SecurityException("Token不合法！");
        }

    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes != null;
        return attributes.getRequest();
    }
}

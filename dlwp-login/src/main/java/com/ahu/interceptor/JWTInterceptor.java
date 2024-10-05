package com.ahu.interceptor;

import com.ahu.constant.JwtClaimsConstant;
import com.ahu.context.BaseContext;
import com.ahu.pojo.entity.JwtProperties;
import com.ahu.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT拦截器
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties properties;
    /**
     * 配置JWT拦截器
     * @param request 请求
     * @param response 响应
     * @param handler 拦截器
     * @return 是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //1.判断handle类型，非动态直接放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        try{
            //2.从请求头上直接获取到JWT
            String token = request.getHeader(properties.getTokenName()); //这个要和前端商量好
            //3.判断token是否合法
            Claims claims = JWTUtil.parseJWT(properties.getSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(empId);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}

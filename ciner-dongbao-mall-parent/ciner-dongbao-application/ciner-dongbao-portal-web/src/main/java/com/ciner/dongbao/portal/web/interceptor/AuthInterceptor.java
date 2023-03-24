package com.ciner.dongbao.portal.web.interceptor;




import com.alibaba.nacos.common.utils.StringUtils;
import com.ciner.dongbao.common.base.TokenException;
import com.ciner.dongbao.common.base.annotations.TokenCheck;
import com.ciner.dongbao.common.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器，解析注解
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器进入");

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            throw new TokenException("token 为空");
            //return false;
        }

        //控制编辑方法
        HandlerMethod handlerMethod = (HandlerMethod)handler;

        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);

            if (annotation.required()){
                //校验token
                try {
                    JwtUtil.parseToken(token);

                    return true;
                }catch (Exception e){
                    throw new TokenException("token 异常");
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

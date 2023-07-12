package com.example.interceptor;

import com.example.controller.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession httpSession;

    //Controller逻辑执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle....");
        String uri = request.getRequestURI();
        System.out.println("当前路径："+uri);

        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if(!isHandlerMethod){
            System.out.println("不是方法上");
            return false;
        }
        //不需要登录的注解
        //需要登录验证
        String token = request.getHeader("token");

        if(null == token){
            System.out.println("无token");
        }else {
            boolean claims;
            try{
                claims = TokenUtil.verify(token);
                if(!claims){
                    System.out.println("token有误");
                    return false;
                }
            }catch (Exception e){
                System.out.println("token有误");
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle....");
    }

    //Controller逻辑和视图解析器执行完毕
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion....");
    }
}


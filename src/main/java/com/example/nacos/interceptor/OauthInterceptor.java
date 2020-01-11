package com.example.nacos.interceptor;

import com.example.nacos.utils.LanguageUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 语言拦截器
 */
public class OauthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LanguageUtils languageUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取语言信息
        String language = request.getHeader("Accept-Language");
        //设置语言环境
        languageUtils.setLanguage(language);
        return super.preHandle(request, response, handler);
    }
}
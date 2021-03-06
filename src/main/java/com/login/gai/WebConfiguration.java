package com.login.gai;

import com.login.gai.handler.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lenovo on 2019/8/12.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 日志拦截器
     */
    @Autowired
    private LogInterceptor logInterceptor;
    /**
     * 重写添加拦截器方法并添加配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }
}

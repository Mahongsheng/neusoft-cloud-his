package com.neu.his.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").
                excludePathPatterns("/login", "/loginPost",
                        "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg"
                        , "/**/fonts/*");
    }
}

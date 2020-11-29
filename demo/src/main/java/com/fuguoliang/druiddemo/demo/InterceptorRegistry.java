package com.fuguoliang.druiddemo.demo;

import com.fuguoliang.druiddemo.demo.interceptors.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author FGL_S
 */
@Configuration
public class InterceptorRegistry implements WebMvcConfigurer {
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/hello/*");
    }
}

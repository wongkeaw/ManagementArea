package com.aran.tech.managementArea.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggingMVCConfig implements WebMvcConfigurer {
	
	@Autowired
	LoggingInterceptor handlerInterceptorAdapter ;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorAdapter).addPathPatterns("/api/**/**");
    }
}

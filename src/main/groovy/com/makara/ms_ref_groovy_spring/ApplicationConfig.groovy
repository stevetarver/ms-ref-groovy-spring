package com.makara.ms_ref_groovy_spring

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.makara.ms_ref_groovy_spring.common.interceptor.RequestInterceptor())
    }
}

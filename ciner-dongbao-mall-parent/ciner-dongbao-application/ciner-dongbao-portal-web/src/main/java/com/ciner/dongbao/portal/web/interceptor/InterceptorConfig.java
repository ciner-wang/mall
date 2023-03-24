package com.ciner.dongbao.portal.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/ums-member/login")
                .excludePathPatterns("/code/generator");
    }

    /**
     * 将拦截器加入容器
     * @return
     */
    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}

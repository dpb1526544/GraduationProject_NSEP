package com.dpb.nsepback.config;

import com.dpb.nsepback.config.interceptor.JwtInterceptor;
import com.dpb.nsepback.config.interceptor.BugControllerGuardInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器设置
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bugControllerGuardInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/user/login", "/user/register", "/Mail/**", "/user/resetpwd", "/Download") //放行登录注册、邮箱验证码、文件下载
                .excludePathPatterns("/**/*.html", "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.ttf");  // 放行静态文件
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    public BugControllerGuardInterceptor bugControllerGuardInterceptor() {
        return new BugControllerGuardInterceptor();
    }

}

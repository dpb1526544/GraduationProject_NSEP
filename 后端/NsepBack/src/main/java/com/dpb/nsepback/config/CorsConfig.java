package com.dpb.nsepback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

// 配置文件
@Configuration
public class CorsConfig {
    @Value("${nsep.cors.allowed-origins}")
    private String allowedOrigins;

    // 当前跨域请求最大有效时长。这里设置为1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 1 设置访问源地址
        // Because setAllowCredentials(true) is enabled below, CORS must use explicit origins (wildcard "*" is forbidden).
        Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !"*".equals(s))
                .forEach(corsConfiguration::addAllowedOrigin);
        if (corsConfiguration.getAllowedOrigins() == null || corsConfiguration.getAllowedOrigins().isEmpty()) {
            throw new IllegalStateException("Invalid nsep.cors.allowed-origins: [" + allowedOrigins + "]. "
                    + "When credentials are enabled, wildcard '*' is not allowed; configure explicit origins such as http://localhost:8080");
        }

        // 2 设置访问源请求头
        corsConfiguration.addAllowedHeader("*");

        // 3 设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(MAX_AGE);

        // 4 对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}

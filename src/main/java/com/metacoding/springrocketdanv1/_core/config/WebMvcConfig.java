package com.metacoding.springrocketdanv1._core.config;

import com.metacoding.springrocketdanv1._core.interceptor.CompanyInterceptor;
import com.metacoding.springrocketdanv1._core.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**")// pattern 주소에 요청이 오면 발동
                .addResourceLocations("file:./upload/") // file: <- 파일 프로토콜, "./" <- 프로젝트 경로
                .setCachePeriod(60 * 60) // 초 단위 => 한시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/company/**")
                .excludePathPatterns("/company")
                .excludePathPatterns("/company/{id:\\d+}");

        registry.addInterceptor(new CompanyInterceptor())
                .addPathPatterns("/company/update-form")
                .addPathPatterns("/company/update")
                .addPathPatterns("/job/**")
                .excludePathPatterns("/job")
                .excludePathPatterns("/job/{id:\\d+}")
                .excludePathPatterns("/job/{id:\\d+}/bookmark");
    }
}

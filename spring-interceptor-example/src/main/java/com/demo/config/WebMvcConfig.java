package com.demo.config;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptor.v1.SpringInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.demo.*")
public class WebMvcConfig implements WebMvcConfigurer  {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
   
	@Bean
	public SpringInterceptor springInterceptor() {
	    return new SpringInterceptor();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(springInterceptor()).addPathPatterns("/v1/*");
	}
	
}

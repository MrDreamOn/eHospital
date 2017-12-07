package com.hospital.register.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringInterceptorRegister extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SpringInterceptorRegister.class);
    
    /* 
     * 添加spring中的拦截器
     */
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JackInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new JeffInterceptor())
                .addPathPatterns("/freemarker/**");
        super.addInterceptors(registry);
    }*/
    
    /* 
     * 注册静态文件的自定义映射路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ///image/**  这个是url请求路径
        //classpath:/image/ 去这个路径下寻找静态文件
    	logger.info("");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
    
}

package com.hospital.register.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * pagehelper
 * @author paul
 *
 */
@Configuration
public class MybatisConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);
    

	@Bean
    public PageHelper pageHelper(){
		logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}

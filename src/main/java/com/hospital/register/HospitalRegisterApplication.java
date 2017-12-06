package com.hospital.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 相当于@Configuration,@EnableAutoConfiguration,@ComponentScan
 * @author paul
 *
 */
@SpringBootApplication(scanBasePackages ={"com.hospital.register"})
@MapperScan("com.hospital.register.dao")
public class HospitalRegisterApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HospitalRegisterApplication.class, args);
		} catch (BeanCreationException e) {
			System.err.println("未正确启动中医预约挂号系统，请检查配置是否OK。");
			e.printStackTrace();
		}

	}

}

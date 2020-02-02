package org.sang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
//@MapperScan("org.sang.mapper")
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class
})
@EnableScheduling
public class MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}
}

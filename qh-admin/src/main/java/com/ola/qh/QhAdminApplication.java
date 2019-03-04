package com.ola.qh;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.ola.qh.dao")
public class QhAdminApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(QhAdminApplication.class);
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(QhAdminApplication.class, args);
	}
}

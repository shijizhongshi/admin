package com.ola.qh;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class OlaTradeApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(OlaTradeApplication.class);
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OlaTradeApplication.class, args);
	}
}

package com.aca.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;


@SpringBootApplication
@EnableSwagger2
public class SpringBoot_aca_Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot_aca_Application.class, args);
	}

	@Bean
	public ViewResolver myViewReolver(){
		return new MyViewResolver();
	}

	public static class MyViewResolver implements ViewResolver{

		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			return null;
		}
	}
}

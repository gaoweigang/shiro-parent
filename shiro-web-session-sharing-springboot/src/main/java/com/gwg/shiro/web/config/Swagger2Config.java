package com.gwg.shiro.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {



	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.houbank.market.controller"))//
				.build()//
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("shiro 系统 API")//
				.description("")//描述
				.license("")//
				.licenseUrl("http://unlicense.org")//
				.termsOfServiceUrl("")//
				.version("0.0.1")//版本号
				.contact(new Contact("", "", ""))//创建人
				.build();
	}

}

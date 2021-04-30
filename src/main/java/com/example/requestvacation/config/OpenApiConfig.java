package com.example.requestvacation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
    		.title("Request-Vacation API").version("v1.0")
            .description("휴가 신청 시스템 API 명세서 입니다.")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact().name("김수민").url("https://github.com/sooomni").email("klynn0403@gmail.com"))
            .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

    return new OpenAPI()
            .components(new Components())
            .info(info);
  }

}
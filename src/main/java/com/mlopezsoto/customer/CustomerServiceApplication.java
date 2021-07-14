package com.mlopezsoto.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Spring Boot Application for Customer Services.
 */
@EnableOpenApi
@EnableCaching
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	/**
	 * Configures Swagger UI
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(new ApiInfoBuilder().
						title("Customer Services")
						.description("API Documentation for Customer Services")
						.build())

				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mlopezsoto.customer"))
				.paths(PathSelectors.any())
				.build();
	}

}

package com.mybank.accounts;

import com.mybank.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value =(AccountContactInfoDto.class) )
@OpenAPIDefinition(info = @Info(
		title = "Account microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "Uday Shastrakar",
				email = "uday.shastrakar@gmail.com"
		)
))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

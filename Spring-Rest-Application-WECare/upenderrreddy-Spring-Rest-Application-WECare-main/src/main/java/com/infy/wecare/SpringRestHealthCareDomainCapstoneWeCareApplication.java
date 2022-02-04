package com.infy.wecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class SpringRestHealthCareDomainCapstoneWeCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestHealthCareDomainCapstoneWeCareApplication.class, args);
	}

}

package com.example.springfoxbug;

import com.example.springfoxbug.configuration.DummyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableWebFlux
@Import(DummyConfiguration.class)
@EnableSwagger2WebFlux
public class SpringfoxBugApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringfoxBugApplication.class, args);
	}
}

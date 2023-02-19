package com.javacrud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootCrudOperationApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootCrudOperationApplication.class, args);
		log.info("SpringBootCrudOperationApplication successfully Run.");

	}

}

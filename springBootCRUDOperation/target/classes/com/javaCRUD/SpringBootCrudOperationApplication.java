package com.javaCRUD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudOperationApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudOperationApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudOperationApplication.class, args);
		logger.info("SpringBootCrudOperationApplication successfully Run.");

	}

}

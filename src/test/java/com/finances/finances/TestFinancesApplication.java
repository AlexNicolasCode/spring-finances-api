package com.finances.finances;

import org.springframework.boot.SpringApplication;

public class TestFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.from(FinancesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

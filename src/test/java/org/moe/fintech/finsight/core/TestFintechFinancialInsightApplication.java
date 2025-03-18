package org.moe.fintech.finsight.core;

import org.springframework.boot.SpringApplication;

public class TestFintechFinancialInsightApplication {

	public static void main(String[] args) {
		SpringApplication.from(FintechFinancialInsightApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

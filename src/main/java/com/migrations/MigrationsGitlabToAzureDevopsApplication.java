package com.migrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrationsGitlabToAzureDevopsApplication {

	public static void main(String[] args) throws Exception {
		System.exit(SpringApplication.exit(SpringApplication.run(MigrationsGitlabToAzureDevopsApplication.class, args)));
	}
}

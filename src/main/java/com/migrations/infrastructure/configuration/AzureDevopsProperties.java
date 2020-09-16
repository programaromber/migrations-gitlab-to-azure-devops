package com.migrations.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Component
@ConfigurationProperties("migrations.azure.devops")
public class AzureDevopsProperties {
	
	private String uri;
	private String token;
	private String username;
	private String password;
	private String apiVersion;
	private String project;
	private String organization;

}

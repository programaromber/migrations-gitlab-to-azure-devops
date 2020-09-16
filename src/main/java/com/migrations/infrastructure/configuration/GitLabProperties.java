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
@ConfigurationProperties("migrations.gitlab")
public class GitLabProperties {
	
	private String uri;
	private String token;
	private String username;
	private String password;
	private String path;
	private String repository;

}

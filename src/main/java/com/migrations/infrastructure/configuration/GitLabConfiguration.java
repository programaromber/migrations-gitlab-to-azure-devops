package com.migrations.infrastructure.configuration;

import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitLabConfiguration {
	
	@Autowired
	private GitLabProperties gitLabProperties;

	@Bean
	public GitLabApi gitLabApi() {
		return new GitLabApi(gitLabProperties.getUri(), gitLabProperties.getToken());
	}
	
}

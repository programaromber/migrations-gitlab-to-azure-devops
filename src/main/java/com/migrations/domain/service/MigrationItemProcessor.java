package com.migrations.domain.service;

import org.gitlab4j.api.models.Project;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.migrations.infrastructure.configuration.AzureDevopsProperties;
import com.migrations.infrastructure.model.RepositoryDTO;

public class MigrationItemProcessor implements ItemProcessor<Project, RepositoryDTO> {

	@Autowired
	private AzureDevopsProperties azureDevopsProperties;

	@Override
	public RepositoryDTO process(final Project project) throws Exception {
		return new RepositoryDTO(azureDevopsProperties.getProject(), project.getName(), project.getHttpUrlToRepo());
	}

}

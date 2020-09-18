package com.migrations.domain.service;

import org.gitlab4j.api.models.Project;
import org.springframework.batch.item.ItemProcessor;

import com.migrations.infrastructure.model.RepositoryDTO;

public class MigrationItemProcessor implements ItemProcessor<Project, RepositoryDTO> {

	@Override
	public RepositoryDTO process(final Project project) throws Exception {
		return new RepositoryDTO(project.getName(), project.getHttpUrlToRepo());
	}

}

package com.migrations.domain.service;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.migrations.infrastructure.configuration.AzureDevopsProperties;
import com.migrations.infrastructure.model.RepositoryDTO;
import com.migrations.infrastructure.service.AzureDevopsClientService;

@Component
public class ProjectWriter implements ItemWriter<RepositoryDTO> {

	
	@Autowired
	private AzureDevopsClientService azureDevopsClientService;
	
	@Autowired
	private AzureDevopsProperties azureDevopsProperties;
	
	public void write(List<? extends RepositoryDTO> repositories) throws Exception {
		
		final String projectName = azureDevopsProperties.getProject();
		final String organization = azureDevopsProperties.getOrganization();
		
		azureDevopsClientService.createAzureProject(projectName, organization);
		
		repositories.stream().forEach(
				repository -> {
					azureDevopsClientService.migrateRepositoryToAzure(repository, projectName, organization);
				}
		);
		
	}
}

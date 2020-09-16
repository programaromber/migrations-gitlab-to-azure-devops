package com.migrations.infrastructure.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.migrations.infrastructure.model.RepositoryDTO;
import com.migrations.infrastructure.util.AzureDevopsUtils;
import com.migrations.infrastructure.util.GitUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AzureDevopsClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AzureDevopsUtils azureDevopsUtil;
	
	@Autowired
	private GitUtils gitUtils;
	

	public void createAzureProject(String project, String organization) {
		try {
			restTemplate.postForEntity(azureDevopsUtil.getUrlCreateProject(organization),
					azureDevopsUtil.getRequestCreateProject(project, organization), String.class);
			log.info(String.format("[%s] PROJECT CREATE", project));
		} catch (Exception e) {
			log.info(String.format("[%s] PROJECT EXISTS", project));
		}

	}

	public void migrateRepositoryToAzure(RepositoryDTO repository, String project, String organization) {
		createAzureRepository(repository, project, organization);
		mirrorRepositoryToAzure(repository, project, organization);
	}

	private void createAzureRepository(RepositoryDTO repository, String project, String organization) {
		try {
			restTemplate.postForEntity(azureDevopsUtil.getUrlCreateRepository(organization, project),
					azureDevopsUtil.getRequestCreateRepository(repository.getName()), String.class);
			log.info(String.format("[%s] %s: REPOSITORY CREATE", project, repository.getName()));
		} catch (Exception e) {
			log.info(String.format("[%s] %s: REPOSITORY EXISTS", project, repository.getName()));
		}
	}

	private void mirrorRepositoryToAzure(RepositoryDTO repository, String project, String organization) {
		File localTempPath = null;
		try {
			localTempPath = gitUtils.getTemporaryPath(repository.getName());
			gitUtils.cloneRepository(repository, localTempPath);
			log.info(String.format("[%s] %s: MIRROR CLONE)", project, repository.getUrl()));
			String azureRepoUrl = gitUtils.getUrlAzureReposiptory(project, repository.getName());
			gitUtils.pushToAzure(localTempPath, azureRepoUrl);
			log.info(String.format("[%s] %s: MIRROR PUSH", project, azureRepoUrl));
		} catch (Exception e) {
			log.error(String.format("[%s] %s: ERROR CLONE OR PUSH", project, repository.getName()), e);
		} finally {
			localTempPath.deleteOnExit();;
		}
	}

}

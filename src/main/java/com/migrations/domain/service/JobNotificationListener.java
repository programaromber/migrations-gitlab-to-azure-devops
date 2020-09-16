package com.migrations.domain.service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.migrations.infrastructure.configuration.AzureDevopsProperties;
import com.migrations.infrastructure.configuration.GitLabProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobNotificationListener extends JobExecutionListenerSupport {
	
	
	@Autowired
	private GitLabProperties gitLabProperties;
	
	@Autowired
	private AzureDevopsProperties azureDevopsProperties;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("START PROCESS");
		log.info(String.format("GITLAB PATH: %s", gitLabProperties.getPath()));
		log.info(String.format("GITLAB PATH: %s", gitLabProperties.getRepository()));
		log.info(String.format("AZURE DEVOPS ORGANIZATION: %s", azureDevopsProperties.getOrganization()));
		log.info(String.format("AZURE DEVOPS PROJECT: %s", azureDevopsProperties.getProject()));
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("FINISHED PROCESS");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			log.info("FAILURE PROCESS");
		}
	}
}

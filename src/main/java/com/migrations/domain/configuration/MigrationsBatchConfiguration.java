package com.migrations.domain.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.gitlab4j.api.models.Project;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.migrations.domain.service.JobNotificationListener;
import com.migrations.domain.service.MigrationItemProcessor;
import com.migrations.domain.service.ProjectReader;
import com.migrations.infrastructure.configuration.GitLabProperties;
import com.migrations.infrastructure.model.RepositoryDTO;
import com.migrations.infrastructure.service.GitLabClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class MigrationsBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobs;
	
	@Autowired
	public StepBuilderFactory steps;
	
	@Autowired
	private GitLabClientService gitLabService;
	
	@Autowired
	private GitLabProperties gitLabProperties;
	
	
	@Bean
	public ItemReader<Project> reader() {
		List<String> namespaces = gitLabService.findAllNamespaces().stream()
				.filter( n ->  n.getFullPath().contains(gitLabProperties.getPath()))
						.map(p -> p.getName().toLowerCase()).collect(Collectors.toList());
		
		List<Project> projects = gitLabService.findAllProjects().stream()
				.filter(p -> 
				(gitLabProperties.getRepository() == "" || p.getName().equals(gitLabProperties.getRepository()) ) &&
				namespaces.stream()
						.anyMatch(n -> n.toLowerCase().contains(p.getNamespace().getFullPath().toLowerCase())) 
		).collect(Collectors.toList());
		log.info(String.format("[%s] LOAD PROJECTS: %d", gitLabProperties.getPath(), projects.size()));
		return new ProjectReader(projects);
	}

	@Bean
	public MigrationItemProcessor processor() {
		return new MigrationItemProcessor();
	}
	
	public void loadProjects() {
		
	}

	@Bean
	public Job migrationsJob(JobNotificationListener listener, Step step) {
		
		return jobs.get("migrationsJob")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step)
			.end()
			.build();
	}
	
	
	@Bean
	public Step step1(ItemWriter<RepositoryDTO> writer) {
		return steps.get("step1")
			.<Project, RepositoryDTO> chunk(100)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}



	
}

package com.migrations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.core.StepExecution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.migrations.domain.service.MigrationItemProcessor;

@SpringBootTest
@ActiveProfiles("test")
class MigrationsGitlabToAzureDevopsApplicationTests {
	
	@Mock
	protected StepExecution stepExecution;
	
	@InjectMocks
	private MigrationItemProcessor migrationItemProcessor = new MigrationItemProcessor();
	
	private Project getMockProject() {
		Project project =  new Project();
		project.setHttpUrlToRepo("https://github.com/rafasall/migrations-gitlab-to-azure-devops.git");
		project.setName("migrations-gitlab-to-azure-devops");
        return project;
    }
	
	@Test
	void testProcess() {
		try {
			assertNotNull(migrationItemProcessor.process(getMockProject()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

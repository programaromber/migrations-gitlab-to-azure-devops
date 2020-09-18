package com.migrations.infrastructure.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.models.Namespace;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
class GitLabClientServiceTests  {

	@Mock
	GitLabClientService service;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findAllNamespaces() {
		List<Namespace> namespaces = this.service.findAllNamespaces();
		when(namespaces).thenReturn(new ArrayList<>());
		assertEquals(0, namespaces.size());
	}
	

	@Test
	void findAllProjects() {
		List<Project> projects = this.service.findAllProjects();
		assertTrue(projects != null);
		when(projects).thenReturn(new ArrayList<>());
		assertEquals(0, projects.size());
	}

	@Test
	void findAllProjectsByPath() {
		List<Project> projects = this.service.findAllProjectsByPath("teste");
		when(projects).thenReturn(new ArrayList<>());
		assertEquals(0, projects.size());
	}


}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class GitLabClientServiceTests {

//	@Mock
	@Autowired
	GitLabClientService service;

//	@Before
//	public void initialize() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	void findAllNamespaces() {
		List<Namespace> namespaces = this.service.findAllNamespaces();
		assertTrue(namespaces != null);
//		when(namespaces).thenReturn(new ArrayList<>());
//		assertEquals(true, namespaces != null);
	}
	

	@Test
	void findAllProjects() {
		List<Project> projects = this.service.findAllProjects();
		assertTrue(projects != null);
//		when(projects).thenReturn(new ArrayList<>());
//		assertEquals(true, projects != null);
	}

	@Test
	void findAllProjectsByPath() {
		List<Project> projects = this.service.findAllProjectsByPath("coe");
		assertTrue(projects != null);
//		when(projects).thenReturn(new ArrayList<>());
//		assertEquals(true, projects != null);
	}

}

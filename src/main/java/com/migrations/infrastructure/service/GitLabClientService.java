package com.migrations.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Namespace;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GitLabClientService {
	
	@Autowired
	private GitLabApi gitLabApi;
	
	public List<Namespace> findAllNamespaces() {
		try {
			
			return this.gitLabApi.getNamespaceApi()
					.getNamespacesStream().
					filter(n ->
							n.getFullPath().matches("[a-zA-Z\s_-]+") &&
							n.getKind().equals("group") &&
							!n.getName().equals("test"))
					.collect(Collectors.toList());
		} catch (GitLabApiException e) {
			log.error("Erro de comunição com serviço GITLAB.", e);
			return null;
		}
	}
	
	public Namespace findNamespaceByPath(String path) {
		try {
			
			return (Namespace) this.gitLabApi.getNamespaceApi().getGroupIdOrPath(path);
		} catch (GitLabApiException e) {
			log.error("Erro de comunição com serviço GITLAB.", e);
			return null;
		}
	}
	
	
	
	public List<Project> findAllProjects() {
		try {
			return this.gitLabApi.getProjectApi().getProjects();
		} catch (GitLabApiException e) {
			log.error("Erro de comunição com serviço GITLAB.", e);
			return null;
		}
	}
	
	public List<Project> findAllProjectsByPath(String path) {
		try {
			return this.gitLabApi.getProjectApi().getProjects(path);
		} catch (GitLabApiException e) {
			log.error("Erro de comunição com serviço GITLAB.", e);
			return null;
		}
	}
	
}

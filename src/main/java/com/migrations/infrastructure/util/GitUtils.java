package com.migrations.infrastructure.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.migrations.infrastructure.configuration.AzureDevopsProperties;
import com.migrations.infrastructure.configuration.GitLabProperties;
import com.migrations.infrastructure.model.RepositoryDTO;

@Component
public class GitUtils {
	
	@Autowired
	private GitLabProperties gitLabProperties;
	
	@Autowired
	private AzureDevopsProperties azureDevopsProperties;
	
	public File getTemporaryPath(String repository) {
		return new File(System.getProperty("java.io.tmpdir") + File.separator + repository);
	}
	
	public String getUrlAzureReposiptory(String project, String repository) {
		return String.format("https://%s@dev.azure.com/%s/%s/_git/%s", azureDevopsProperties.getOrganization(), azureDevopsProperties.getOrganization(), project, repository);
	}
	
	public void cloneRepository(RepositoryDTO repository, File localTempPath) throws InvalidRemoteException, TransportException, GitAPIException {
		Git.cloneRepository()
		.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitLabProperties.getUsername(), gitLabProperties.getPassword()))
		.setURI(repository.getUrl())
		.setMirror(true)
		.setDirectory(localTempPath)
		.call();
	}
	
	public void pushToAzure(File localTempPath, String repositoryUrl) throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		Git.open(localTempPath)
				.push()
				.setCredentialsProvider(new UsernamePasswordCredentialsProvider(azureDevopsProperties.getUsername(), azureDevopsProperties.getPassword()))
				.setRemote(repositoryUrl)
				.setPushAll()
				.call();
	}
	
}

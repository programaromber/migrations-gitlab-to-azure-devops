package com.migrations.infrastructure.util;

import java.util.Collections;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.migrations.infrastructure.configuration.AzureDevopsProperties;

@Component
public class AzureDevopsUtils {
	
	@Autowired
	private AzureDevopsProperties properties;
	
	
	public String getUrlCreateProject(String organization) {
		return String.format("%s/%s/_apis/projects?api-version=%s", properties.getUri(), organization, properties.getApiVersion());
	}
	
	public String getUrlCreateRepository(String organization, String project) {
		return String.format("%s/%s/%s/_apis/git/repositories?api-version=%s", properties.getUri(), organization, project, properties.getApiVersion());
	}
	
	
	public HttpEntity<String> getRequestCreateProject(String project, String organization) throws JSONException {
		return new HttpEntity<String>(getProjectBodyRequest(project, organization), getHttpHeaders());
	}
	
	public HttpEntity<String> getRequestCreateRepository(String repository) throws JSONException {
		return new HttpEntity<String>(getRepositoryBodyRequest(repository), getHttpHeaders());
	}
	
	
	public String getProjectBodyRequest(String project, String organization) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", project);
		jsonObject.put("description", project);
		jsonObject.put("capabilities", getCapabilities());
		return jsonObject.toString();
	}

	public JSONObject getCapabilities() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("versioncontrol", getVersionControl());
		jsonObject.put("processTemplate", getProcessTemplate());
		return jsonObject;
	}
	
	public JSONObject getVersionControl() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sourceControlType", "Git");
		return jsonObject;
	}
	
	public JSONObject getProcessTemplate() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("templateTypeId", "6b724908-ef14-45cf-84f8-768b5384da45");
		return jsonObject;
	}
	
	public String getRepositoryBodyRequest(String repository) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", repository);
		return jsonObject.toString();
	}
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth("", properties.getToken());
		return	headers;
	}

}

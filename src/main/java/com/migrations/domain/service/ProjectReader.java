package com.migrations.domain.service;

import java.util.List;

import org.gitlab4j.api.models.Project;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProjectReader implements ItemReader<Project> {
 
    private int nextIndex;
    private List<Project> projects;
    
    public ProjectReader(List<Project> projects) {
		this.projects = projects;
	}
    
	@Override
	public Project read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Project nextProject = null;
		  
        if (nextIndex < projects.size()) {
        	nextProject = projects.get(nextIndex);
            nextIndex++;
        }
        else {
        	nextIndex = 0;
        }
 
        return nextProject;
	}

 
}
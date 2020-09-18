package com.migrations.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RepositoryDTO {

	private String name;
	private String url;
	
}

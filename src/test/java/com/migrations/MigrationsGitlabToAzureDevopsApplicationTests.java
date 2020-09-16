package com.migrations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ActiveProfiles("test")
class MigrationsGitlabToAzureDevopsApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(true, true);
	}

}

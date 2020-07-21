package com.wellsfargo.serv_req_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@ComponentScan(basePackages =  "com.wellsfargo")
@PropertySources({
		// @PropertySource(value =
		// "file:${ENV_HOME}/config/services/taskmanagement.properties")
		@PropertySource(value = "classpath:taskmanagement.properties"),
		@PropertySource(value = "classpath:db_auth_user.properties") })
@EnableJpaRepositories
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}

package com.wellsfargo.serv_req_center.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

@RestController
//@RequestMapping("")
public class TaskManagementController {

	// for time being at class level
	List<ServiceRequestTask> tasks = null;

	@GetMapping("/getServiceReqTasks")
	public @ResponseBody ResponseEntity<List<ServiceRequestTask>> getServiceReqTasks() {
		// List<ServiceRequestTask> tasks = null;
		if (tasks == null) {
			tasks = loadTasks();
		}
		return ResponseEntity.ok(tasks);
	}

	private List<ServiceRequestTask> loadTasks() {
		List<ServiceRequestTask> jsonTasks = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonTasks = mapper.readValue(
					new File(TaskManagementController.class.getResource("/data/task-list.json").getFile()),
					new TypeReference<List<ServiceRequestTask>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonTasks;
	}

}

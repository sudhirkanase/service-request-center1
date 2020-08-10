package com.wellsfargo.srca.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.beans.ServiceRequestTask;
import com.wellsfargo.srca.task_management.service.TaskManagementService;
import com.wellsfargo.srca.task_management.service.TaskValidatorService;

@RestController
//@RequestMapping("")
public class TaskManagementController {

	@Autowired
	TaskManagementService taskService;

	@Autowired
	TaskValidatorService validatorService;

	// Temp path for file upload
	private static String UPLOAD_FOLDER = "file_upload";

	@GetMapping("/getServiceReqTasks")
	public ResponseEntity<List<ServiceRequestTask>> getServiceReqTasks() {
		return ResponseEntity.ok(taskService.getServiceReqTasks());
	}

	@PostMapping("/save/{taskType}")
	public ResponseEntity save(@PathVariable("taskType") String taskType, @RequestBody String taskJson) {
		ServiceRequestTask servReqBean = taskService.convertJsonObjToBean(taskType, taskJson);
		validatorService.validate(servReqBean);
		taskService.saveTask(servReqBean);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/getTaskDetails")
	public @ResponseBody ResponseEntity<ServiceRequestTask> createNewTask(
			@RequestBody ServiceRequestTask contactCenterReq) {

		if (contactCenterReq.getAccountNo() == null || contactCenterReq.getAccountNo() < 0) {
			// throw exception
		}

		ServiceRequestTask contactDetail = null;
		if (contactDetail == null) {
			// Create New Task
			if (contactCenterReq.getId() == 0) {
				contactDetail = taskService.createNewTask(contactCenterReq.getAccountNo());
			} else {
				// Update existing task details
				contactDetail = taskService.loadContactDetail(contactCenterReq.getAccountNo(),
						contactCenterReq.getId());
			}
		}
		return ResponseEntity.ok(contactDetail);
	}

}

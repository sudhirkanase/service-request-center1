package com.wellsfargo.serv_req_center.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.task_management.beans.Account;
import com.wellsfargo.serv_req_center.task_management.beans.ContactCenterDetail;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;
import com.wellsfargo.serv_req_center.task_management.service.TaskManagementService;

/**
 * Rest Controller for contact Center Detail
 * 
 * @author Heta Shah
 *
 */
@RestController
public class ContactCenterController {

	@Autowired
	private TaskManagementService taskManagementService;

	@PostMapping("/contactCenterDetail")
	public @ResponseBody ResponseEntity<ContactCenterDetail> getContactDetail(
			@RequestBody ServiceRequestTask requestBody) {
		ContactCenterDetail contactDetail = null;
		contactDetail = taskManagementService.loadContactDetail(requestBody.getId());
		return ResponseEntity.ok(contactDetail);
	}

	@PostMapping("/createNewTask")
	public @ResponseBody ResponseEntity<ContactCenterDetail> createNewTask(@RequestBody Integer acctNumber,
			String taskType) {

		if (acctNumber == null || acctNumber < 0) {
			// throw exception
		}

		ContactCenterDetail contactDetail = null;
		if (contactDetail == null) {
			contactDetail = taskManagementService.createNewTask(acctNumber);
		}
		return ResponseEntity.ok(contactDetail);
	}

	@PostMapping("/saveTask")
	public @ResponseBody ResponseEntity<ContactCenterDetail> saveTask(@RequestBody ContactCenterDetail details,
			String taskType) {

		// validation will come here
		/*
		 * if( acctNumber== null || acctNumber < 0) { // throw exception }
		 */

		ContactCenterDetail contactDetail = null;
		if (contactDetail == null) {
			contactDetail = taskManagementService.saveTask(details);
		}
		return ResponseEntity.ok(contactDetail);
	}

}

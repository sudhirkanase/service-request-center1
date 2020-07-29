package com.wellsfargo.serv_req_center.task_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/getTaskDetails")
	public @ResponseBody ResponseEntity<ContactCenterDetail> createNewTask(
			@RequestBody ServiceRequestTask contactCenterReq) {

		if (contactCenterReq.getAccountNo() == null || contactCenterReq.getAccountNo() < 0) {
			// throw exception
		}

		ContactCenterDetail contactDetail = null;
		if (contactDetail == null) {
			// Create New Task
			if (contactCenterReq.getId() == 0) {
				contactDetail = taskManagementService.createNewTask(contactCenterReq.getAccountNo());
			} else {
				// Update existing task details
				contactDetail = taskManagementService.loadContactDetail(contactCenterReq.getAccountNo(),
						contactCenterReq.getId());
			}
		}
		return ResponseEntity.ok(contactDetail);
	}

	@PostMapping("/saveTask")
	public @ResponseBody ResponseEntity<ContactCenterDetail> saveTask(@RequestBody ContactCenterDetail details) {

		ContactCenterDetail contactDetail = null;
		if (contactDetail == null) {
			contactDetail = taskManagementService.saveTask(details);
		}
		return ResponseEntity.ok(contactDetail);
	}

}

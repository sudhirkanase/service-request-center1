package com.wellsfargo.serv_req_center.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.serv_req_center.task_management.beans.Account;
import com.wellsfargo.serv_req_center.task_management.beans.AccountType;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;
import com.wellsfargo.serv_req_center.task_management.service.AccountService;
import com.wellsfargo.serv_req_center.task_management.service.TaskManagementService;

@RestController
public class AccountController {

	@Autowired
	AccountService accService;
	
	@Autowired
	TaskManagementService taskService;

	@PostMapping("/searchAccounts")
	public ResponseEntity<List<Account>> searchAccounts(@RequestBody AccountType account) {
		return ResponseEntity.ok(accService.searchAccounts(account));
	}

	@GetMapping("/getAccount/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") Integer accountNumber) {
		return ResponseEntity.ok(accService.getAccount(accountNumber));
	}
	
	@PostMapping("/getTaskList")
	public ResponseEntity<List<ServiceRequestTask>> getTaskList(@RequestBody Integer accountNo ) {
		return ResponseEntity.ok(taskService.getTaskList(accountNo));
	}

}

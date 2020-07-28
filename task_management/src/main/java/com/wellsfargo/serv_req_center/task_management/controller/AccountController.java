package com.wellsfargo.serv_req_center.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.serv_req_center.task_management.beans.Account;
import com.wellsfargo.serv_req_center.task_management.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accService;

	@GetMapping("/searchAccounts/{accountNumber}/{accountName}")
	public ResponseEntity<List<Account>> searchAccounts(@PathVariable("accountNumber") String accountNumber,
			@PathVariable("accountName") String accountName) {
		return ResponseEntity.ok(accService.searchAccounts(accountNumber, accountName));
	}

	@GetMapping("/getAccount/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") Integer accountNumber) {
		return ResponseEntity.ok(accService.getAccount(accountNumber));
	}

}

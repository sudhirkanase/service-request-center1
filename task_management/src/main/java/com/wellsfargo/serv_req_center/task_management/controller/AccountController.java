package com.wellsfargo.serv_req_center.task_management.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.task_management.beans.Account;
import com.wellsfargo.serv_req_center.task_management.commons.DataNotFoundException;


@RestController
public class AccountController {
	
	
	@GetMapping("/searchAccounts/{accountNumber}/{accountName}")
	public ResponseEntity<List<Account>> searchAccounts(@PathVariable("accountNumber") String accountNumber,@PathVariable("accountName") String accountName) {
		 List<Account> accounts = null;
		 List<Account> filteredAccounts = null;
		if (accounts == null) {
			accounts = loadAccounts();
			filteredAccounts=accounts.stream().filter(accnt->String.valueOf(accnt.getAccountNumber()).startsWith(accountNumber) || accnt.getAccountName().startsWith(accountName)).collect(Collectors.toList());
			
		}
		if(filteredAccounts.isEmpty()) {
			throw new DataNotFoundException("Accounts not found");
		}
		return ResponseEntity.ok(filteredAccounts);
	}
	
	
	@GetMapping("/getAccount/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
		 List<Account> accounts = null;
		 Account account=null;
		if (accounts == null) {
			accounts = loadAccounts();
			Integer acctNo=Integer.parseInt(accountNumber);
			Optional<Account> filteredAccount=accounts.stream().filter(accnt->accnt.getAccountNumber().equals(acctNo)).findFirst();
			if(filteredAccount.isPresent()) {
				account=filteredAccount.get();
			}
		}
		return ResponseEntity.ok(account);
	}
	
	private List<Account> loadAccounts() {
		List<Account> jsonAccounts = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonAccounts = mapper.readValue(
					getClass().getResource("/data/account-list.json"),
					new TypeReference<List<Account>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonAccounts;
	}

}

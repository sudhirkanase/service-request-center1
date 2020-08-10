package com.wellsfargo.srca.task_management.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.srca.task_management.beans.Account;
import com.wellsfargo.srca.task_management.beans.AccountType;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;
import com.wellsfargo.srca.task_management.exception.SrcaException;

@Service
public class AccountService {

	// temp at account level
	List<Account> accounts = null;

	public List<Account> searchAccounts(AccountType account) {
		// List<Account> accounts = null;
		List<Account> filteredAccounts = null;
		if(account.getAccountNumber() == null && account.getAccountName() == null) {
			throw new SrcaException("Either accountNumber or accountName is required");
		}
		if (accounts == null) {
			accounts = loadAccounts();
		}
		if(account.getAccountNumber() != null && account.getAccountName() != null) {
		filteredAccounts = accounts.stream()
				.filter(accnt -> String.valueOf(accnt.getAccountNumber()).startsWith(String.valueOf(account.getAccountNumber()))
						|| accnt.getAccountName().startsWith(account.getAccountName()))
				.collect(Collectors.toList());
		}else if(account.getAccountNumber() != null){
			filteredAccounts=accounts.stream().filter(accnt->String.valueOf(accnt.getAccountNumber()).startsWith(String.valueOf(account.getAccountNumber()))).collect(Collectors.toList());
		}else if(account.getAccountName() != null) {
			filteredAccounts=accounts.stream().filter(accnt-> accnt.getAccountName().startsWith(account.getAccountName())).collect(Collectors.toList());
		}
		if (filteredAccounts.isEmpty()) {
			throw new DataNotFoundException("Accounts not found");
		}
		return filteredAccounts;
	}

	public Account getAccount(Integer accountNumber) {

		Account account = null;
		if (accounts == null) {
			accounts = loadAccounts();
		}
		//Integer acctNo = Integer.parseInt(accountNumber);
		Optional<Account> filteredAccount = accounts.stream().filter(accnt -> accnt.getAccountNumber().equals(accountNumber))
				.findFirst();
		if (filteredAccount.isPresent()) {
			account = filteredAccount.get();
		}
		return account;
	}

	private List<Account> loadAccounts() {
		List<Account> jsonAccounts = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonAccounts = mapper.readValue(getClass().getResource("/data/account-list.json"),
					new TypeReference<List<Account>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonAccounts;
	}

}

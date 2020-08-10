package com.wellsfargo.srca.task_management.beans;

import java.io.Serializable;

public class Account implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer accountNumber;
	private String accountName;
	private Integer mainAccountNumber;
	
	private Integer marketValue;
	private String branchName;
	private Integer branchCode;
	private String revTrackingDescription;
	private Integer revTrackingCode;
	private String administrator;
	private Integer adminCode;
	private String seniorAdministrator;
	private Integer seniorAdministratorCode;
	private String backupAdministrator;
	private Integer backupAdministratorCode;
	private String investmentManager;
	private Integer investmentManagerCode;
	private String backupInvestmentManager;
	private Integer backupInvestmentManagerCode;
	private String bankCapacity;
	private String controlGroup;
	private String accountShortName;
	
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getMainAccountNumber() {
		return mainAccountNumber;
	}
	public void setMainAccountNumber(Integer mainAccountNumber) {
		this.mainAccountNumber = mainAccountNumber;
	}
	public Integer getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Integer marketValue) {
		this.marketValue = marketValue;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getRevTrackingDescription() {
		return revTrackingDescription;
	}
	public void setRevTrackingDescription(String revTrackingDescription) {
		this.revTrackingDescription = revTrackingDescription;
	}
	public Integer getRevTrackingCode() {
		return revTrackingCode;
	}
	public void setRevTrackingCode(Integer revTrackingCode) {
		this.revTrackingCode = revTrackingCode;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	
	public Integer getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(Integer adminCode) {
		this.adminCode = adminCode;
	}
	public String getSeniorAdministrator() {
		return seniorAdministrator;
	}
	public void setSeniorAdministrator(String seniorAdministrator) {
		this.seniorAdministrator = seniorAdministrator;
	}
	public Integer getSeniorAdministratorCode() {
		return seniorAdministratorCode;
	}
	public void setSeniorAdministratorCode(Integer seniorAdministratorCode) {
		this.seniorAdministratorCode = seniorAdministratorCode;
	}
	public String getBackupAdministrator() {
		return backupAdministrator;
	}
	public void setBackupAdministrator(String backupAdministrator) {
		this.backupAdministrator = backupAdministrator;
	}
	public Integer getBackupAdministratorCode() {
		return backupAdministratorCode;
	}
	public void setBackupAdministratorCode(Integer backupAdministratorCode) {
		this.backupAdministratorCode = backupAdministratorCode;
	}
	public String getInvestmentManager() {
		return investmentManager;
	}
	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}
	public Integer getInvestmentManagerCode() {
		return investmentManagerCode;
	}
	public void setInvestmentManagerCode(Integer investmentManagerCode) {
		this.investmentManagerCode = investmentManagerCode;
	}
	public String getBackupInvestmentManager() {
		return backupInvestmentManager;
	}
	public void setBackupInvestmentManager(String backupInvestmentManager) {
		this.backupInvestmentManager = backupInvestmentManager;
	}
	public Integer getBackupInvestmentManagerCode() {
		return backupInvestmentManagerCode;
	}
	public void setBackupInvestmentManagerCode(Integer backupInvestmentManagerCode) {
		this.backupInvestmentManagerCode = backupInvestmentManagerCode;
	}
	public String getBankCapacity() {
		return bankCapacity;
	}
	public void setBankCapacity(String bankCapacity) {
		this.bankCapacity = bankCapacity;
	}
	public String getControlGroup() {
		return controlGroup;
	}
	public void setControlGroup(String controlGroup) {
		this.controlGroup = controlGroup;
	}
	public String getAccountShortName() {
		return accountShortName;
	}
	public void setAccountShortName(String accountShortName) {
		this.accountShortName = accountShortName;
	}
	

}

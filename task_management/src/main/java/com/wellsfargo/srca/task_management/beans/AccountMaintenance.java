package com.wellsfargo.srca.task_management.beans;

import javax.validation.constraints.NotBlank;

public class AccountMaintenance extends ServiceRequestTask {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String changeDescription;
	private String taskPriority;
	@NotBlank
	private String additionalInformation;
	private String branchName;
	private String administrator;
	public String getChangeDescription() {
		return changeDescription;
	}
	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}
	public String getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

}

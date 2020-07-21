package com.wellsfargo.serv_req_center.task_management.beans;

import java.io.Serializable;

public class ServiceRequestTask implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String taskType;
	private String taskSpecific;
	private String workflowStep;
	private String accountNo;
	private String accountName;
	private String requesterName;
	private String dueDate;

	public ServiceRequestTask() {

	}

	public ServiceRequestTask(long id, String taskType, String taskSpecific, String workflowStep, String accountNo,
			String accountName, String requesterName, String dueDate) {
		this.id = id;
		this.taskType = taskType;
		this.taskSpecific = taskSpecific;
		this.workflowStep = workflowStep;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.requesterName = requesterName;
		this.dueDate = dueDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskSpecific() {
		return taskSpecific;
	}

	public void setTaskSpecific(String taskSpecific) {
		this.taskSpecific = taskSpecific;
	}

	public String getWorkflowStep() {
		return workflowStep;
	}

	public void setWorkflowStep(String workflowStep) {
		this.workflowStep = workflowStep;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}

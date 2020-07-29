package com.wellsfargo.serv_req_center.task_management.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.serv_req_center.task_management.beans.ContactCenterDetail;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

@Service
public class TaskManagementService {

	@Autowired
	AccountService accService;

	@Autowired
	LoggedInUserInfoCache userCahce;

	// for time being at class level
	List<ServiceRequestTask> tasks = null;
	ContactCenterDetail contactDetails = new ContactCenterDetail();

	public List<ServiceRequestTask> getServiceReqTasks() {
		// List<ServiceRequestTask> tasks = null;
		if (tasks == null) {
			tasks = loadTasks();
		}
		return tasks;
	}

	public ContactCenterDetail loadContactDetail(Integer acctNumber, long id) {

		// Get task data from task list
		ServiceRequestTask taskDetail = tasks.stream().filter(taskList -> taskList.getId() == id).findAny()
				.orElse(null);
		// Set task data in contact detail
		contactDetails.setId(taskDetail.getId());
		contactDetails.setStatus(taskDetail.getStatus());
		contactDetails.setEmail(taskDetail.getEmail());
		contactDetails.setAssignedEmail(taskDetail.getAssignedEmail());
		contactDetails.setPhone(taskDetail.getPhone());
		contactDetails.setAccountService(taskDetail.getAccountService());
		contactDetails.setCreatedDate(taskDetail.getCreatedDate());
		contactDetails.setAssignedUserGroup(taskDetail.getAssignedUserGroup());
		// Set account data to contact Detail
		contactDetails.setAccountDetail(accService.getAccount(acctNumber));
		return contactDetails;
	}

	private List<ServiceRequestTask> loadTasks() {
		List<ServiceRequestTask> jsonTasks = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonTasks = mapper.readValue(getClass().getResource("/data/task-list.json"),
					new TypeReference<List<ServiceRequestTask>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonTasks;
	}

	public ContactCenterDetail createNewTask(Integer acctNumber) {
		ContactCenterDetail details = new ContactCenterDetail();
		details.setAccountDetail(accService.getAccount(acctNumber));

		// To get max taskId and add +1 to it
		ServiceRequestTask src = Collections.max(tasks, Comparator.comparing(s -> s.getId()));
		details.setId(src.getId() + 1);

		// Set user details
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userDetails instanceof UserDetails) {
			details.setAccountService(((UserDetails) userDetails).getUsername());
			details.setEmail(((UserDetails) userDetails).getUsername() + "@Wellsfargo.com");
			details.setAssignedEmail(((UserDetails) userDetails).getUsername() + "-assigned@Wellsfargo.com");
			details.setAccountService(((UserDetails) userDetails).getUsername());
			details.setAssignedUserGroup(((UserDetails) userDetails).getUsername() + " group");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			details.setCreatedDate((String) dtf.format(now));
			details.setStatus("Open");
			details.setPhone("(888) 479-7699");
		}

		return details;
	}

	public ContactCenterDetail saveTask(ContactCenterDetail details) {
		
		ServiceRequestTask taskDetail = tasks.stream().
				filter(taskList -> taskList.getId() == details.getId()).findAny()
				.orElse(null);
		
		details.setDueDate("20/08/20");
		details.setTaskSpecific("Online");
		details.setWorkflowStep("Contact Center Entity");
		details.setAccountNo(details.getAccountDetail().getAccountNumber());
		details.setAccountName(details.getAccountDetail().getAccountName());

		//Set user details for requester
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			details.setRequesterName(((UserDetails) userDetails).getUsername());
		}
		
		//if already exists then update the data
		if(taskDetail == null) {
			tasks.add(details);
		} else {
			tasks.set(tasks.indexOf(taskDetail), details);
		}
		
		contactDetails = details;
		
		return (ContactCenterDetail)details;
	}

}

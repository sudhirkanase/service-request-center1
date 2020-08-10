package com.wellsfargo.serv_req_center.task_management.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.wellsfargo.serv_req_center.task_management.beans.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.serv_req_center.task_management.beans.ContactCenterDetail;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;
import com.wellsfargo.serv_req_center.task_management.exception.TaskType;

@Service
public class TaskManagementService {

	@Autowired
	AccountService accService;

	@Autowired
	DocumentService documentService;

	@Autowired
	LoggedInUserInfoCache userCache;

	// for time being at class level
	List<ServiceRequestTask> tasks = null;

	public List<ServiceRequestTask> getServiceReqTasks() {
		if (tasks == null) {
			tasks = loadTasks();
		}

		// Sort ID by descending order
		tasks = tasks.stream().sorted(Comparator.comparing(ServiceRequestTask::getId).reversed())
				.collect(Collectors.toList());

		return tasks;
	}

	public ServiceRequestTask loadContactDetail(Integer acctNumber, long id) {

		// Get task data from task list
		ServiceRequestTask taskDetail = tasks.stream().filter(taskList -> taskList.getId() == id).findAny()
				.orElse(null);

		List<Document> docList = null;

		if (documentService.getDocumentList() != null) {
			docList = documentService.getDocumentList().stream().filter(document -> document.getTaskId() == id).collect(Collectors.toList());
		}

		if (taskDetail != null) {
			// Set account data to contact Detail
			taskDetail.setDocuments(docList);
			taskDetail.setAccountDetail(accService.getAccount(acctNumber));
		}
		return taskDetail;
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
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			details.setCreatedDate((String) dtf.format(now));
			details.setStatus("Open");
			details.setPhone("(888) 479-7699");
		}

		return details;
	}

	public ServiceRequestTask saveTask(ServiceRequestTask details) {

		ServiceRequestTask taskDetail = tasks.stream().filter(taskList -> taskList.getId() == details.getId()).findAny()
				.orElse(null);

		details.setDueDate("20/08/20");
		details.setTaskSpecific("Online");
		details.setWorkflowStep("Contact Center Entity");
		details.setAccountNo(details.getAccountDetail().getAccountNumber());
		details.setAccountName(details.getAccountDetail().getAccountName());
		// Set user details for requester
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			details.setRequesterName(((UserDetails) userDetails).getUsername());
		}

		// if already exists then update the data
		if (taskDetail == null) {
			tasks.add(details);
		} else {
			tasks.set(tasks.indexOf(taskDetail), details);
		}

		return details;
	}

	public List<ServiceRequestTask> getTaskList(Integer accountNo) {

		List<ServiceRequestTask> taskList = tasks.stream().filter(task -> task.getAccountNo().equals(accountNo))
				.collect(Collectors.toList());
		return taskList;
	}

	public ServiceRequestTask convertJsonObjToBean(String taskType, String taskJson) {
		ServiceRequestTask servReqBean = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			TaskType task = null;
			task = TaskType.valueOf(taskType);

			switch (task) {
			case CONTACTCENTER:
				servReqBean = mapper.readValue(taskJson, ContactCenterDetail.class);
				break;
			case ACCOUNT:
				break;
			default:
				throw new Exception("Invalid task type:- " + taskType);
			}
		} catch (Exception e) {
			// To be thrown proper exception message
			e.printStackTrace();
		}

		return servReqBean;
	}

}

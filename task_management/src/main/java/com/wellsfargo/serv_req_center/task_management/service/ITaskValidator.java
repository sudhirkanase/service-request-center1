package com.wellsfargo.serv_req_center.task_management.service;

import java.util.Set;

import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

public interface ITaskValidator {

	Set<String> validate(ServiceRequestTask task);
}

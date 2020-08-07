package com.wellsfargo.serv_req_center.task_management.validator;

import java.util.Set;

import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

public interface IServiceRequestValidator<T extends ServiceRequestTask> {

	Set<String> validate(T task);
	
}

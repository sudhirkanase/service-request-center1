package com.wellsfargo.srca.task_management.validator;

import java.util.Set;

import com.wellsfargo.srca.task_management.beans.ServiceRequestTask;

public interface IServiceRequestValidator<T extends ServiceRequestTask> {

	Set<String> validate(T task);
	
}

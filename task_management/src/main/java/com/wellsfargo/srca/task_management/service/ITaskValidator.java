package com.wellsfargo.srca.task_management.service;

import java.util.Set;

import com.wellsfargo.srca.task_management.beans.ServiceRequestTask;

public interface ITaskValidator {

	Set<String> validate(ServiceRequestTask task);
}

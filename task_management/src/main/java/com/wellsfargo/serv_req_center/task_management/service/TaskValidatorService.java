package com.wellsfargo.serv_req_center.task_management.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;
import com.wellsfargo.serv_req_center.task_management.exception.DataNotFoundException;
import com.wellsfargo.serv_req_center.task_management.exception.SrcaValidationException;
import com.wellsfargo.serv_req_center.task_management.validator.ServiceRequestValidator;

@Service
public class TaskValidatorService implements ITaskValidator {

	@Autowired
	ServiceRequestValidator validator;

	@Override
	public Set<String> validate(ServiceRequestTask contactDetail) {
		Set<String> validate = validator.validate(contactDetail);
		if(validate.size() > 0) {
			throw new SrcaValidationException(String.join(",", validate));
		}
		return validate;
	}

}

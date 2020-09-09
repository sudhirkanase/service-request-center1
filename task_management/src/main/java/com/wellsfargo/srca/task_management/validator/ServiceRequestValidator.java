package com.wellsfargo.srca.task_management.validator;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wellsfargo.srca.task_management.beans.ServiceRequestTask;

@Component
@Qualifier("BaseServiceRequestValidator")
public class ServiceRequestValidator implements IServiceRequestValidator<ServiceRequestTask> {

	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestValidator.class);
	@Override
	public Set<String> validate(ServiceRequestTask task) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		// Generic bean validations
		Set<ConstraintViolation<ServiceRequestTask>> errors = validator.validate(task);
		if (errors.isEmpty()) {
			return Collections.emptySet();
		}

		Set<String> errs = errors.stream().map(error -> error.getPropertyPath() + "  -- " + error.getMessage())
				.collect(Collectors.toSet());

		// Custom or complex validation can be handled manually here if any
		
		return errs;
	}

	public static void main(String[] args) {
		ServiceRequestTask task = new ServiceRequestTask();

		ServiceRequestValidator validator = new ServiceRequestValidator();
		Set<String> errors = validator.validate(task);
		errors.forEach(error -> logger.error(error));
	}
}

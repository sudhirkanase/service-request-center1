package com.wellsfargo.srca.task_management.validator;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ActionValidator implements ConstraintValidator<ActionConstraint, Object> {

	private String field1;
	private String field2;

	String[] clientInquiryActions = { "Distributed Inquiry", "Market Value", "Closing Account Status",
			"Closed Account Inquiry" };

	String[] clientContactActions = { "Client Returned Call", "Client Call For..", "Information not Disclosed" };

	String[] cashTransactionsActions = { "ACH Disbursement", " Stop Payment/Re-Issue", "ACH Receipt" };

	String[] generalInformationActions = { " General Information request", "Multiple client request" };

	public void initialize(ActionConstraint constraint) {
		this.field1 = constraint.value()[0];
		this.field2 = constraint.value()[1];
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Boolean valid = false;
		Object callCodeValue = new BeanWrapperImpl(value).getPropertyValue(field1);
		Object actionValue = new BeanWrapperImpl(value).getPropertyValue(field2);
		if(callCodeValue != null && actionValue != null) {
		valid =  callCodeValue.equals("Client Inquiry") ? Arrays.asList(clientInquiryActions).contains(actionValue)
				: callCodeValue.equals("Client Contact") ? Arrays.asList(clientContactActions).contains(actionValue)
						: callCodeValue.equals("Cash Transactions")
								? Arrays.asList(cashTransactionsActions).contains(actionValue)
								: callCodeValue.equals("General Information")
										? Arrays.asList(generalInformationActions).contains(actionValue)
										: false;
		}
		return valid;
	}

}

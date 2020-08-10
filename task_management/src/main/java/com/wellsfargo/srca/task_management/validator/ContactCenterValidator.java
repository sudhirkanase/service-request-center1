package com.wellsfargo.srca.task_management.validator;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ContactCenterValidator implements ConstraintValidator<ContactCenterConstraint, Object> {

	private String field1;
	private String field2;
	private String field3;
	private String field4;

	String[] clientInquiryActions = { "Distributed Inquiry", "Market Value", "Closing Account Status",
			"Closed Account Inquiry" };

	String[] clientContactActions = { "Client Returned Call", "Client Call For..", "Information not Disclosed" };

	String[] cashTransactionsActions = { "ACH Disbursement", " Stop Payment/Re-Issue", "ACH Receipt" };

	String[] generalInformationActions = { " General Information request", "Multiple client request" };

	public void initialize(ContactCenterConstraint constraint) {
		this.field1 = constraint.value()[0];
		this.field2 = constraint.value()[1];
		this.field3 = constraint.value()[2];
		this.field4 = constraint.value()[3];
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Boolean validAction = false;
		Boolean validTaxpayer = false;
		Object callCodeValue = new BeanWrapperImpl(value).getPropertyValue(field1);
		Object actionValue = new BeanWrapperImpl(value).getPropertyValue(field2);
		Object isTaxpayerIdValue = new BeanWrapperImpl(value).getPropertyValue(field3);
		Object taxpayerIdValue = new BeanWrapperImpl(value).getPropertyValue(field4);

		if (callCodeValue != null && actionValue != null) {
			validAction = isActionValid(callCodeValue, actionValue);
		}

		validTaxpayer = isvalidTaxpayer(isTaxpayerIdValue, taxpayerIdValue);

		if (!validAction && !validTaxpayer) {
			customMessageForValidation(context, "Invalid Action Selected, Taxpayer Id must not be Null");
		} else if (!validTaxpayer) {
			customMessageForValidation(context, "Taxpayer Id must not be Null");
		} else if (!validAction) {
			customMessageForValidation(context, "Invalid Action Selected");
		}

		return validAction && validTaxpayer;
	}

	/**
	 * 
	 * @param callCodeValue
	 * @param actionValue
	 * @return boolean To validate Action Value against the Call Code selected
	 */
	public boolean isActionValid(Object callCodeValue, Object actionValue) {
		return callCodeValue.equals("Client Inquiry") ? Arrays.asList(clientInquiryActions).contains(actionValue)
				: callCodeValue.equals("Client Contact") ? Arrays.asList(clientContactActions).contains(actionValue)
						: callCodeValue.equals("Cash Transactions")
								? Arrays.asList(cashTransactionsActions).contains(actionValue)
								: callCodeValue.equals("General Information")
										? Arrays.asList(generalInformationActions).contains(actionValue)
										: false;
	}

	/**
	 * 
	 * @param isTaxpayerIdValue
	 * @param taxpayerIdValue
	 * @return boolean To Validate Taxpayer Id value should not be null if the
	 *         IsTaxpayerId flag value is yes
	 */
	public boolean isvalidTaxpayer(Object isTaxpayerIdValue, Object taxpayerIdValue) {
		if (isTaxpayerIdValue != null && isTaxpayerIdValue.equals("yes")
				&& (taxpayerIdValue == null || taxpayerIdValue.equals(""))) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param constraintContext
	 * @param message
	 * To Set Custom message for validation error
	 */
	private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
		// build new violation message and add it
		constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}

}

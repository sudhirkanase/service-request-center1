package com.wellsfargo.serv_req_center.task_management.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.task_management.beans.ContactCenterDetail;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

@Service
public class TaskManagementService {

	public ContactCenterDetail loadContactDetail(ServiceRequestTask requestContactDetail) {
		ContactCenterDetail jsonDetails = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			jsonDetails = mapper.readValue(getClass().getResource("/data/contact-center.json"),
					new TypeReference<ContactCenterDetail>() {
					});
			if(requestContactDetail.getAccountNo().equals("11231100")) {
				jsonDetails.getAccountDetail().setAccountNumber(11231100);
				jsonDetails.getAccountDetail().setAccountShortName("Test 01");
				jsonDetails.getAccountDetail().setMainAccountNumber(3411230);
				jsonDetails.getAccountDetail().setMarketValue(371572);
				jsonDetails.getAccountDetail().setBranchCode(197);
			} else if(requestContactDetail.getAccountNo().equals("11233300")) {
				jsonDetails.getAccountDetail().setAccountNumber(11233300);
				jsonDetails.getAccountDetail().setAccountShortName("Test 02");
				jsonDetails.getAccountDetail().setMainAccountNumber(351001);
				jsonDetails.getAccountDetail().setMarketValue(112566);
				jsonDetails.getAccountDetail().setBranchCode(231);
			} else if(requestContactDetail.getAccountNo().equals("112351")) {
				jsonDetails.getAccountDetail().setAccountNumber(112351);
				jsonDetails.getAccountDetail().setAccountShortName("Test 03");
				jsonDetails.getAccountDetail().setMainAccountNumber(980011);
				jsonDetails.getAccountDetail().setMarketValue(54512);
				jsonDetails.getAccountDetail().setBranchCode(155);
			} else if(requestContactDetail.getAccountNo().equals("11235300")) {
				jsonDetails.getAccountDetail().setAccountNumber(11235300);
				jsonDetails.getAccountDetail().setAccountShortName("Test 04");
				jsonDetails.getAccountDetail().setMainAccountNumber(845119);
				jsonDetails.getAccountDetail().setMarketValue(255633);
				jsonDetails.getAccountDetail().setBranchCode(197);
			} else if(requestContactDetail.getAccountNo().equals("11235400")) {
				jsonDetails.getAccountDetail().setAccountNumber(11235400);
				jsonDetails.getAccountDetail().setAccountShortName("Test 05");
				jsonDetails.getAccountDetail().setMainAccountNumber(7510012);
				jsonDetails.getAccountDetail().setMarketValue(152281);
				jsonDetails.getAccountDetail().setBranchCode(231);
			}
			
			if(requestContactDetail.getId() == 0) {
				jsonDetails.setDocuments(null);
				jsonDetails.setCallerName(null);
				jsonDetails.setCallCode(null);
				jsonDetails.setCallDetails(null);
				jsonDetails.setCallerPhone(null);
				jsonDetails.setAction(null);
				jsonDetails.setFullyAuthenticated(null);
				jsonDetails.setIsTaxpayerId(null);
				jsonDetails.setTaxpayerId(null);
				jsonDetails.setTaskNotes(null);
				jsonDetails.setTaskPriority(null);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonDetails;
	}
}

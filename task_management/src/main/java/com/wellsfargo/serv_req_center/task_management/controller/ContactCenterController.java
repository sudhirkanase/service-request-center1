package com.wellsfargo.serv_req_center.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.serv_req_center.task_management.beans.Account;
import com.wellsfargo.serv_req_center.task_management.beans.ContactCenterDetail;
import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;

/**
 * Rest Controller for contact Center Detail
 * @author Heta Shah
 *
 */
@RestController
public class ContactCenterController {

	@PostMapping("/contactCenterDetail")
	public @ResponseBody ResponseEntity<ContactCenterDetail> getContactDetail(
			@RequestBody ServiceRequestTask requestBody) {
		
		ContactCenterDetail contactDetail = null;
		if (contactDetail == null) {
			contactDetail = loadContactDetail(requestBody);
		}
		return ResponseEntity.ok(contactDetail);
	}

	private ContactCenterDetail loadContactDetail(ServiceRequestTask contactDetail) {
		ContactCenterDetail jsonDetails = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			if (contactDetail.getId() == 0) {
				jsonDetails = mapper.readValue(
						getClass().getResource("/data/add-contact-center.json"),
						new TypeReference<ContactCenterDetail>() {
						});
			} else {
				jsonDetails = mapper.readValue(
						getClass().getResource("/data/contact-center.json"),
						new TypeReference<ContactCenterDetail>() {
						});
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonDetails;
	}
}

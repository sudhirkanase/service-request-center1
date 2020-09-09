package com.wellsfargo.srca.task_management.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.wellsfargo.srca.task_management.beans.Audit;
import com.wellsfargo.srca.task_management.beans.Communication;
import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;

@Service
public class CommunicationService {
	List<Communication> communicationList = null;
	AtomicInteger atomicInteger = new AtomicInteger(1);

	@Autowired
	private AuditService auditService;

	public List<Communication> saveCommunication(Communication communication) {
		communication.setCommunicationId((long) atomicInteger.getAndIncrement());
		if (communicationList == null) {
			communicationList = new ArrayList<Communication>();
		}
		communicationList.add(communication);

		List<Communication> filteredCommunicationList = null;

		Predicate<Communication> byAccountNOAndTaskId = comm -> comm.getTaskId() == communication.getTaskId()
				&& comm.getAccountNumber() == communication.getAccountNumber();
		filteredCommunicationList = communicationList.stream().filter(byAccountNOAndTaskId)
				.collect(Collectors.toList());

		// To set Audit details
		Audit auditDetails = new Audit();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		auditDetails.setDate((String) dtf.format(now));
		auditDetails.setTaskId(communication.getTaskId());
		auditDetails.setAuditType("Communication Details Created");
		auditDetails.setAction("Entered Note: Communication Details Created");
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			auditDetails.setUser(((UserDetails) userDetails).getUsername());
		}
		// To Save Audit data
		auditService.saveAuditDetails(auditDetails);

		return filteredCommunicationList;
	}

	public List<Communication> getCommunicationList() {
		return communicationList;
	}

	public List<Communication> deleteByCommunicationId(long communicationId) {
		
		Communication communication = communicationList.stream()
				.filter(comm -> comm.getCommunicationId() == communicationId).findAny()
				.orElse(null);
		
		if (communicationList.removeIf((Communication comm) -> comm.getCommunicationId() == communicationId)) {
			// To set Audit details
			Audit auditDetails = new Audit();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
			LocalDateTime now = LocalDateTime.now();
			auditDetails.setDate((String) dtf.format(now));
			auditDetails.setTaskId(communication.getTaskId());
			auditDetails.setAuditType("Communication Details Deleted");
			auditDetails.setAction("Entered Note: Communication Details Deleted");
			Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetails instanceof UserDetails) {
				auditDetails.setUser(((UserDetails) userDetails).getUsername());
			}
			// To Save Audit data
			auditService.saveAuditDetails(auditDetails);
			
			List<Communication> commListRes = communicationList.stream().filter(commObj -> commObj.getTaskId() == communication.getTaskId())
					.collect(Collectors.toList());

			return commListRes;
		} else {
			throw new DataNotFoundException("Communication Details not found" + communicationId);
		}

	}
}

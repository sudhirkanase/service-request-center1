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

import com.wellsfargo.srca.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.srca.task_management.beans.Audit;
import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;

@Service
public class DocumentService {

	@Autowired
	LoggedInUserInfoCache userCache;

	@Autowired
	AuditService auditService;

	List<Document> documentList = null;
	AtomicInteger atomicInteger = new AtomicInteger(1);

	public List<Document> saveDocument(Document document) {
		document.setDocumentId((long) atomicInteger.getAndIncrement());
		if (documentList == null) {
			documentList = new ArrayList<Document>();
		}
		documentList.add(document);

		List<Document> filteredDocumentList = null;

		// To set Audit details
		Audit auditDetails = new Audit();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		auditDetails.setDate((String) dtf.format(now));
		auditDetails.setTaskId(document.getTaskId());
		auditDetails.setAuditType("Document Created");
		auditDetails.setAction("Document Created");
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			auditDetails.setUser(((UserDetails) userDetails).getUsername());
		}
		// To Save Audit data
		auditService.saveAuditDetails(auditDetails);

		Predicate<Document> byAccountNOAndTaskId = doc -> doc.getTaskId() == document.getTaskId()
				&& doc.getAccountNumber() == document.getAccountNumber();
		filteredDocumentList = documentList.stream().filter(byAccountNOAndTaskId).collect(Collectors.toList());

		return filteredDocumentList;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public List<Document> deleteDocumentByDocumentId(long documentId) {
		
		Document document = documentList.stream().filter(documentObj -> documentObj.getDocumentId() == (documentId))
				.findAny().orElse(null);
		
		if (documentList.removeIf((Document doc) -> doc.getDocumentId() == documentId)) {
			// To set Audit details
			Audit auditDetails = new Audit();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
			LocalDateTime now = LocalDateTime.now();
			auditDetails.setDate((String) dtf.format(now));
			auditDetails.setTaskId(document.getTaskId());
			auditDetails.setAuditType("Document Deleted");
			auditDetails.setAction("Document Deleted");
			Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetails instanceof UserDetails) {
				auditDetails.setUser(((UserDetails) userDetails).getUsername());
			}
			// To Save Audit data
			auditService.saveAuditDetails(auditDetails);
			
			return documentList;
		} else {
			throw new DataNotFoundException("Document not found" + documentId);
		}
	}
}

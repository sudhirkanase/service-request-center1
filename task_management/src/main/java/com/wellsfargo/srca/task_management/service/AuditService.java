package com.wellsfargo.srca.task_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wellsfargo.srca.task_management.beans.Audit;

@Service
public class AuditService {
	
	private List<Audit> auditList;
	
    public List<Audit> saveAuditDetails(Audit audit) {
        if (auditList == null) {
        	auditList = new ArrayList<Audit>();
        }
        auditList.add(audit);

        List<Audit> filteredAuditList = null;
        
        if(auditList != null) {
        filteredAuditList = auditList.stream().filter(audits -> audits.getTaskId() == audit.getTaskId())
				.collect(Collectors.toList());
        }

        return filteredAuditList;
    }
    
    public List<Audit> getAuditData(long taskId) {
    	return auditList.stream().filter(audit -> audit.getTaskId() == taskId)
				.collect(Collectors.toList());
    }
    
    public List<Audit> getAuditList() {
    	return auditList;
    }
}

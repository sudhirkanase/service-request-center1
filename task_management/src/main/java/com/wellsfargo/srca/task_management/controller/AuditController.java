package com.wellsfargo.srca.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.srca.task_management.beans.Audit;
import com.wellsfargo.srca.task_management.service.AuditService;

@RestController
public class AuditController {

    @Autowired
    AuditService auditService;

    @PostMapping("/saveAudit")
    public ResponseEntity<List<Audit>> saveAuditDetails(@RequestBody Audit audit) {
        return ResponseEntity.ok(auditService.saveAuditDetails(audit));
    }
}

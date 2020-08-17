package com.wellsfargo.srca.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.srca.task_management.beans.Communication;
import com.wellsfargo.srca.task_management.service.CommunicationService;

@RestController
public class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @PostMapping("/saveCommunication")
    public ResponseEntity<List<Communication>> saveCommunication(@RequestBody Communication communication) {
        return ResponseEntity.ok(communicationService.saveCommunication(communication));
    }

    @PostMapping("/deleteCommunication")
    public ResponseEntity<List<Communication>> deleteCommunication(@RequestBody long communicationId) {
        return ResponseEntity.ok(communicationService.deleteByCommunicationId(communicationId));
    }
}

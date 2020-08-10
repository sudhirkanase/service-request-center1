package com.wellsfargo.srca.task_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.srca.task_management.beans.Account;
import com.wellsfargo.srca.task_management.beans.AccountType;
import com.wellsfargo.srca.task_management.beans.Communication;
import com.wellsfargo.srca.task_management.service.AccountService;
import com.wellsfargo.srca.task_management.service.CommunicationService;

import java.util.List;

@RestController
public class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @PostMapping("/saveCommunication")
    public ResponseEntity<Communication> saveCommunication(@RequestBody Communication communication) {
        return ResponseEntity.ok(communicationService.saveCommunication(communication));
    }
}

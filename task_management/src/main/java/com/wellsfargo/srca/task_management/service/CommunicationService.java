package com.wellsfargo.srca.task_management.service;

import org.springframework.stereotype.Service;

import com.wellsfargo.srca.task_management.beans.Communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CommunicationService {
    List<Communication> communicationList = null;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public Communication saveCommunication(Communication communication) {
        communication.setCommunicationId((long) atomicInteger.getAndIncrement());
        if (communicationList == null) {
            communicationList = new ArrayList<Communication>();
        }
        communicationList.add(communication);
        return communication;
    }
}

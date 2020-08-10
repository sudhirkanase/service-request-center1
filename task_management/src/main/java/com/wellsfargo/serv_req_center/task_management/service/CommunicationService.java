package com.wellsfargo.serv_req_center.task_management.service;

import com.wellsfargo.serv_req_center.task_management.beans.Communication;
import org.springframework.stereotype.Service;

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

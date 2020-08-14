package com.wellsfargo.srca.task_management.service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wellsfargo.srca.task_management.beans.Communication;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;

@Service
public class CommunicationService {
    List<Communication> communicationList = null;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public List<Communication> saveCommunication(Communication communication) {
        communication.setCommunicationId((long) atomicInteger.getAndIncrement());
        if (communicationList == null) {
            communicationList = new ArrayList<Communication>();
        }
        communicationList.add(communication);

        List<Communication> filteredCommunicationList = null;

        Predicate<Communication> byAccountNOAndTaskId = comm -> comm.getTaskId() == communication.getTaskId() && comm.getAccountNumber() == communication.getAccountNumber();
        filteredCommunicationList = communicationList.stream().filter(byAccountNOAndTaskId)
                .collect(Collectors.toList());

        return filteredCommunicationList;
    }

    public List<Communication> getCommunicationList() {
        return communicationList;
    }

    public List<Communication> deleteByCommunicationId(long communicationId) {
        if (communicationList.removeIf((Communication comm) -> comm.getCommunicationId() == communicationId)) {
            return communicationList;
        } else {
            throw new DataNotFoundException("Document not found" + communicationId);
        }

    }

    private Communication findByCommunicationId(long communicationId) {
        Communication communication = communicationList.stream()
                .filter(comm -> comm.getCommunicationId() == communicationId)
                .findAny()
                .orElseThrow(
                        () -> new DataNotFoundException("Document not found" + communicationId));

        return communication;
    }
}

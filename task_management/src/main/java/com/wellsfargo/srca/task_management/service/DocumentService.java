package com.wellsfargo.srca.task_management.service;

import com.wellsfargo.srca.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.srca.task_management.beans.Communication;
import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    LoggedInUserInfoCache userCache;

    List<Document> documentList = null;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public List<Document> saveDocument(Document document) {
        document.setDocumentId((long) atomicInteger.getAndIncrement());
        if (documentList == null) {
            documentList = new ArrayList<Document>();
        }
        documentList.add(document);

        List<Document> filteredDocumentList = null;

        Predicate<Document> byAccountNOAndTaskId = doc -> doc.getTaskId() == document.getTaskId() && doc.getAccountNumber() == document.getAccountNumber();
        filteredDocumentList = documentList.stream().filter(byAccountNOAndTaskId)
                .collect(Collectors.toList());

        return filteredDocumentList;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public  List<Document> deleteDocumentByDocumentId(long documentId) {
        if (documentList.removeIf((Document doc) -> doc.getDocumentId() == documentId)) {
            return documentList;
        } else {
            throw new DataNotFoundException("Document not found" + documentId);
        }
    }

    private Document findByDocumentId(long documentId) {
        Document document = documentList.stream()
                .filter(documentObj -> documentObj.getDocumentId() == (documentId))
                .findAny()
                .orElseThrow(
                        () -> new DataNotFoundException("Document not found" + documentId));

        return document;
    }
}

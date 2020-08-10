package com.wellsfargo.srca.task_management.service;

import com.wellsfargo.srca.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DocumentService {

    @Autowired
    LoggedInUserInfoCache userCache;

    List<Document> documentList = null;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public void saveDocument(Document document) {
        document.setDocumentId((long) atomicInteger.getAndIncrement());
        if (documentList == null) {
            documentList = new ArrayList<Document>();
        }
        documentList.add(document);
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public boolean deleteDocumentByDocumentId(long documentId) {
        Document document = null;
        document = findByDocumentId(documentId);
        if(document != null) {
            return documentList.removeIf((Document doc) -> doc.getDocumentId() == documentId);
        } else{
            return false;
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

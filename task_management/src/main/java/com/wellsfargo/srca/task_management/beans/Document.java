package com.wellsfargo.srca.task_management.beans;

import java.io.Serializable;

/**
 * Uploaded Documents Detail
 *
 * @author Heta Shah
 */
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    private String documentName;
    private long documentTypeId;
    private String dueDate;
    private String added;
    private String notes;
    private String additionalInst;
    private String attachment;
    private String documentAction;
    private long taskId;
    private long documentId;
    private long accountNumber;

    public Document() { }

    public Document(String documentName, long documentTypeId, String dueDate, String added, String notes, String additionalInst, String attachment, String documentAction, long taskId, long documentId, long accountNumber) {
        this.documentName = documentName;
        this.documentTypeId = documentTypeId;
        this.dueDate = dueDate;
        this.added = added;
        this.notes = notes;
        this.additionalInst = additionalInst;
        this.attachment = attachment;
        this.documentAction = documentAction;
        this.taskId = taskId;
        this.documentId = documentId;
        this.accountNumber = accountNumber;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAdditionalInst() {
        return additionalInst;
    }

    public void setAdditionalInst(String additionalInst) {
        this.additionalInst = additionalInst;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDocumentAction() {
        return documentAction;
    }

    public void setDocumentAction(String documentAction) {
        this.documentAction = documentAction;
    }

    public long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}

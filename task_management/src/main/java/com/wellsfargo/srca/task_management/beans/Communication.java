package com.wellsfargo.srca.task_management.beans;

import java.io.Serializable;
import java.util.Date;

public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;
    private long communicationId;
    private long taskId;
    private String communicationType;
    private String communicationReason;
    private String name;
    private String number;
    private Date followUpDate;
    private String notes;

    Communication() {
    }

    public Communication(long communicationId, long taskId, String communicationType, String communicationReason, String name, String number, Date followUpDate, String notes) {
        this.communicationId = communicationId;
        this.taskId = taskId;
        this.communicationType = communicationType;
        this.communicationReason = communicationReason;
        this.name = name;
        this.number = number;
        this.followUpDate = followUpDate;
        this.notes = notes;
    }

    public long getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(long communicationId) {
        this.communicationId = communicationId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getCommunicationReason() {
        return communicationReason;
    }

    public void setCommunicationReason(String communicationReason) {
        this.communicationReason = communicationReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Communication{" +
                "communicationId=" + communicationId +
                ", taskId=" + taskId +
                ", communicationType='" + communicationType + '\'' +
                ", communicationReason='" + communicationReason + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", followUpDate=" + followUpDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}

package com.howtodoinjava.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "alerts")
@Data
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long alert_id;

    private String dedupekey;

    private String description;

    private String source;

    private String location;

    private String service;

    private String base;

    private String status;

    private String severity;

    private int total_events;

    private boolean processed;

    private String assignee;

    public long getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(long alert_id) {
        this.alert_id = alert_id;
    }

    public String getDedupekey() {
        return dedupekey;
    }

    public void setDedupekey(String dedupekey) {
        this.dedupekey = dedupekey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getTotal_events() {
        return total_events;
    }

    public void setTotal_events(int total_events) {
        this.total_events = total_events;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "alert [id=" + alert_id + ", source=" + source +
                ", severity=" + severity + ", service=" + service + "]";
    }
}
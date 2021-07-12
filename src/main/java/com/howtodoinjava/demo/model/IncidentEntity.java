package com.howtodoinjava.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidents")
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long incident_id;

    private String description;

    private String status;

    private String severity;

    private int total_alerts;

    @Column(columnDefinition = "TEXT")
    private String sources;

    private String assignee;

    public long getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(long incident_id) {
        this.incident_id = incident_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getTotal_alerts() {
        return total_alerts;
    }

    public void setTotal_alerts(int total_alerts) {
        this.total_alerts = total_alerts;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "incident [id=" + incident_id + ", sources=" + sources +
                ", severity=" + severity + "]";
    }
}
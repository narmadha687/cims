package com.cims.demo.model;

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

    private long incidentid;

    @Override
    public String toString() {
        return "alert [id=" + alert_id + ", source=" + source +
                ", severity=" + severity + ", service=" + service + "]";
    }
}
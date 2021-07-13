package com.cims.demo.web;

import com.cims.demo.exception.RecordNotFoundException;
import com.cims.demo.model.AlertEntity;
import com.cims.demo.model.EventEntity;
import com.cims.demo.model.IncidentEntity;
import com.cims.demo.service.AlertService;
import com.cims.demo.service.EventService;
import com.cims.demo.service.IncidentService;
import com.cims.demo.service.Shingles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService service;

    @Autowired
    AlertService alertService;

    @Autowired
    IncidentService incidentService;

    @RequestMapping
    public String addEvent(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        model.addAttribute("event", new EventEntity());
        return "event";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String saveEvent(EventEntity event) {

        // Generate dedupe key
        String dedupeKey = String.format("%s:%s:%s", event.getSource(), event.getBase(), event.getService()).toLowerCase();
        event.setDedupekey(dedupeKey);
        service.createEvent(event);

        AlertEntity alert = createAlert(event);
        IncidentEntity incident = createIncident(alert);

        System.out.println("&&&&&&&&&&&&&&&&& ALERT       = " + alert);
        System.out.println("&&&&&&&&&&&&&&&&& INCIDENT    = " + incident);
        System.out.println("&&&&&&&&&&&&&&&&& INCIDENT ID = " + incident.getIncident_id());
        alert.setIncidentid(incident.getIncident_id());
        alertService.createOrUpdateAlert(alert);

        return "redirect:/alerts";
    }

    private AlertEntity createAlert(EventEntity event) {
        AlertEntity x = null;

        // Check for open alerts with dedupe key
        AlertEntity existingAlert = alertService.findAlertsByDedupekeyAndStatus(event.getDedupekey(), "Open");
        if (existingAlert == null) {
            System.out.println("************* New Alert *****************************");
            AlertEntity alert =
                    AlertEntity.builder().dedupekey(event.getDedupekey()).description(event.getDescription()).source(event.getSource())
                            .location(event.getLocation()).service(event.getService()).base(event.getBase())
                            .status("Open").severity(event.getSeverity()).total_events(1).assignee("Unassigned").build();

            alertService.createOrUpdateAlert(alert);
            x = alert;
        } else {
            System.out.println("************* Alert exists with key");
            // Update existing alert
            System.out.println("Updating existing alert");
            existingAlert.setDescription(event.getDescription());
            existingAlert.setLocation(event.getLocation());
            existingAlert.setSeverity(event.getSeverity());
            existingAlert.setTotal_events(existingAlert.getTotal_events() + 1);
            existingAlert.setProcessed(true);
            alertService.createOrUpdateAlert(existingAlert);
            x = existingAlert;
        }

        return x;
    }

    private IncidentEntity createIncident(AlertEntity alert) {
        IncidentEntity x = null;
        boolean incidentUpdated = false;

        // Get all open incidents
        List<IncidentEntity> incidents = incidentService.findIncidentsByStatus("Open");
        for (IncidentEntity incident : incidents) {
            int total_sources = incident.getSources().split(",").length;
            Set<String> sources = new HashSet<>(Arrays.asList(incident.getSources().split(",")));
            if (sources.contains(alert.getSource())) {
                // Update current incident and break
                incident.setDescription(total_sources + " sources affected");
                incident.setTotal_alerts(incident.getTotal_alerts() + 1);
                incidentService.updateIncident(incident);
                incidentUpdated = true;
                x = incident;
                break;
            } else {
                for (String source : sources) {
                    int similarity = Shingles.getSimilarityPercent(source, alert.getSource());
                    if (similarity > 50) {
                        // Update current incident and break
                        ++total_sources;
                        incident.setDescription(total_sources + " sources affected");
                        incident.setSources(incident.getSources() + ", " + alert.getSource());
                        incident.setTotal_alerts(incident.getTotal_alerts() + 1);
                        incidentService.updateIncident(incident);
                        incidentUpdated = true;
                        x = incident;
                        break;
                    }
                }
            }
        }

        // Create new incident
        if (!incidentUpdated) {
            IncidentEntity incident = IncidentEntity.builder()
                    .description(alert.getSource() + " affected")
                    .assignee("Unassigned")
                    .severity(alert.getSeverity())
                    .status("Open")
                    .sources(alert.getSource())
                    .total_alerts(1)
                    .build();
            incidentService.updateIncident(incident);
            x = incident;
        }

        return x;
    }
}

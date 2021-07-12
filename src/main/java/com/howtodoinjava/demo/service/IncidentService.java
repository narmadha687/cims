package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.IncidentEntity;
import com.howtodoinjava.demo.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    IncidentRepository repository;

    public List<IncidentEntity> getAllIncidents() {
        List<IncidentEntity> incidents = repository.findIncidentsByStatus("open");
        incidents.addAll(repository.findIncidentsByStatus("In Progress"));

        return incidents;
    }

    public IncidentEntity getIncidentById(Long id) throws RecordNotFoundException {
        Optional<IncidentEntity> incident = repository.findById(id);

        if (incident.isPresent()) {
            return incident.get();
        } else {
            throw new RecordNotFoundException("No incident record exist for given id");
        }
    }

    public IncidentEntity updateIncident(IncidentEntity entity) {
        Optional<IncidentEntity> incident = repository.findById(entity.getIncident_id());
        if (incident.isPresent()) {
            IncidentEntity newEntity = incident.get();
            newEntity.setStatus(entity.getStatus());
            newEntity.setSeverity(entity.getSeverity());
            newEntity.setAssignee(entity.getAssignee());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }

    public void deleteIncidentById(Long id) throws RecordNotFoundException {
        Optional<IncidentEntity> incident = repository.findById(id);

        if (incident.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No incident record exist for given id");
        }
    }

    public List<IncidentEntity> findIncidentsByStatus(String status) {
        List<IncidentEntity> incidents = repository.findIncidentsByStatus(status);

        return incidents;
    }

}
package com.cims.demo.service;

import com.cims.demo.exception.RecordNotFoundException;
import com.cims.demo.model.AlertEntity;
import com.cims.demo.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    AlertRepository repository;

    public List<AlertEntity> getAllAlerts() {
        List<AlertEntity> result = (List<AlertEntity>) repository.findAll();

        System.out.println("*********** total alerts : " + result.size());
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<AlertEntity>();
        }
    }

    public AlertEntity getAlertById(Long id) throws RecordNotFoundException {
        Optional<AlertEntity> alert = repository.findById(id);

        if (alert.isPresent()) {
            return alert.get();
        } else {
            throw new RecordNotFoundException("No alert record exist for given id");
        }
    }

    public AlertEntity findAlertsByDedupekeyAndStatus(String dedupekey, String status) {
        AlertEntity alert = repository.findByDedupekeyAndStatus(dedupekey, status);

        return alert;
    }

    public AlertEntity createOrUpdateAlert(AlertEntity entity) {
        if (entity.getAlert_id() == 0) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<AlertEntity> alert = repository.findById(entity.getAlert_id());

            if (alert.isPresent()) {
                AlertEntity newEntity = alert.get();
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteAlertById(Long id) throws RecordNotFoundException {
        Optional<AlertEntity> alert = repository.findById(id);

        if (alert.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No alert record exist for given id");
        }
    }
}
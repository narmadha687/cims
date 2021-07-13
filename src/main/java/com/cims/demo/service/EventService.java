package com.cims.demo.service;

import com.cims.demo.exception.RecordNotFoundException;
import com.cims.demo.model.EventEntity;
import com.cims.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    public List<EventEntity> getAllEvents() {
        List<EventEntity> result = (List<EventEntity>) repository.findAll();

		System.out.println("*********** total events : " + result.size());
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<EventEntity>();
        }
    }

    public EventEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EventEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EventEntity createEvent(EventEntity entity) {
        System.out.println("********************* Event  : " + entity);
        entity = repository.save(entity);
        return entity;
//        if (entity.getEvent_id() == 0) {
//            entity = repository.save(entity);
//
//            return entity;
//        } else {
//            Optional<EventEntity> employee = repository.findById(entity.getEvent_id());
//
//            if (employee.isPresent()) {
//                EventEntity newEntity = employee.get();
//                newEntity = repository.save(newEntity);
//
//                return newEntity;
//            } else {
//                entity = repository.save(entity);
//
//                return entity;
//            }
//        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EventEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
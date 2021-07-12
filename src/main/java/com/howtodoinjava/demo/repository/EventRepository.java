package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.AlertEntity;
import com.howtodoinjava.demo.model.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {

}

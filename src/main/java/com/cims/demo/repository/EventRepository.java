package com.cims.demo.repository;

import com.cims.demo.model.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {

}

package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.AlertEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertRepository extends CrudRepository<AlertEntity, Long> {

    AlertEntity findByDedupekeyAndStatus(String dedupekey, String status);
}

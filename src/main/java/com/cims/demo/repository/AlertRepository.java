package com.cims.demo.repository;

import com.cims.demo.model.AlertEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<AlertEntity, Long> {

    AlertEntity findByDedupekeyAndStatus(String dedupekey, String status);

    List<AlertEntity> findByIncidentid(long incident_id);
}

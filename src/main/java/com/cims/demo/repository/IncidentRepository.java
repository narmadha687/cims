package com.cims.demo.repository;

import com.cims.demo.model.IncidentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

    List<IncidentEntity> findIncidentsByStatus(String status);
}

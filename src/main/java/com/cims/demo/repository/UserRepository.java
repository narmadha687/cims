package com.cims.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cims.demo.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}

package com.mattcalimbas.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.taskmanager.models.User;

@Repository
public interface UserRepo extends CrudRepository <User, Long> {
	User findByEmail(String email);
}

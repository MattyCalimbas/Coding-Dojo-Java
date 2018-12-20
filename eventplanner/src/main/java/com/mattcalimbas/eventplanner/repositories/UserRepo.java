package com.mattcalimbas.eventplanner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.eventplanner.models.User;

@Repository
public interface UserRepo extends CrudRepository <User, Long> {
	User findByEmail(String email);
}

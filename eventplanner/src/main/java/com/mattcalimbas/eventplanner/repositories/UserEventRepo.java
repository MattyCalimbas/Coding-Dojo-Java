package com.mattcalimbas.eventplanner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.eventplanner.models.UserEvent;

@Repository
public interface UserEventRepo extends CrudRepository <UserEvent, Long> {

}

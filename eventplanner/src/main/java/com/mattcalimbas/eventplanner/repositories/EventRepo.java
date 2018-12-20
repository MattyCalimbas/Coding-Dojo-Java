package com.mattcalimbas.eventplanner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.eventplanner.models.Event;

@Repository
public interface EventRepo extends CrudRepository <Event, Long>{
}

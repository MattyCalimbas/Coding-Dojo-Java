package com.mattcalimbas.eventplanner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.eventplanner.models.Message;

@Repository
public interface MessageRepo extends CrudRepository <Message, Long> {

}

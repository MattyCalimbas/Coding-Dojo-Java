package com.mattcalimbas.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.taskmanager.models.Task;

@Repository
public interface TaskRepo extends CrudRepository <Task, Long> {
	
}

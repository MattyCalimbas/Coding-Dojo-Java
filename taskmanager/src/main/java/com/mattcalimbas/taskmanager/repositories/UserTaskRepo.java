package com.mattcalimbas.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.taskmanager.models.UserTask;

@Repository
public interface UserTaskRepo extends CrudRepository <UserTask, Long>{

}

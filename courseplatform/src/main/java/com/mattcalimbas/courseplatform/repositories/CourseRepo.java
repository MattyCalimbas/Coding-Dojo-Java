package com.mattcalimbas.courseplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.courseplatform.models.Course;

@Repository
public interface CourseRepo extends CrudRepository <Course, Long> {

}

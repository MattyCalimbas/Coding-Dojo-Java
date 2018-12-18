package com.mattcalimbas.courseplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.courseplatform.models.UserCourse;

@Repository
public interface UserCourseRepo extends CrudRepository <UserCourse, Long> {
}

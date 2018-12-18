package com.mattcalimbas.courseplatform.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mattcalimbas.courseplatform.models.Course;
import com.mattcalimbas.courseplatform.models.User;
import com.mattcalimbas.courseplatform.models.UserCourse;
import com.mattcalimbas.courseplatform.repositories.CourseRepo;
import com.mattcalimbas.courseplatform.repositories.UserCourseRepo;
import com.mattcalimbas.courseplatform.repositories.UserRepo;

@Service
public class CourseService {
	private final UserRepo userRepo;
	private final CourseRepo courseRepo;
	private final UserCourseRepo ucRepo;
	
	public CourseService(UserRepo userRepo, CourseRepo courseRepo, UserCourseRepo ucRepo) {
		this.userRepo = userRepo;
		this.courseRepo = courseRepo;
		this.ucRepo = ucRepo;
	}
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return false;
		} else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	public User findByEmail (String email) {
		return userRepo.findByEmail(email);
	}
	
	public User findUserById(Long id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

	public List<Course> allCourses() {
		return (List<Course>) courseRepo.findAll();
	}

	public void create(Course course) {
		courseRepo.save(course);
		
	}

	public Course findCourseById(Long id) {
		Optional<Course> c = courseRepo.findById(id);
		if(c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}

	public void addUserToCourse(UserCourse uc) {
		ucRepo.save(uc);
		
	}

	public boolean duplicateUser(String email) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            return true;
        }
    }

	public void save(Course course) {
		courseRepo.save(course);
		
	}

	public void updateUser(User u) {
		userRepo.save(u);
		
	}

	public void deleteCourse(Long id) {
		courseRepo.deleteById(id);
		
	}
}

package com.mattcalimbas.taskmanager.services;

import java.util.List;
import java.util.Optional;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.mattcalimbas.taskmanager.models.Task;
import com.mattcalimbas.taskmanager.models.User;
import com.mattcalimbas.taskmanager.repositories.TaskRepo;
import com.mattcalimbas.taskmanager.repositories.UserRepo;
import com.mattcalimbas.taskmanager.repositories.UserTaskRepo;


@Service
public class TaskService {
	private final UserRepo userRepo;
	private final TaskRepo taskRepo;
	private final UserTaskRepo utRepo;
	
	public TaskService(UserRepo userRepo, TaskRepo taskRepo, UserTaskRepo utRepo) {
		this.userRepo = userRepo;
		this.taskRepo = taskRepo;
		this.utRepo = utRepo;
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

	public boolean duplicateUser(String email) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            return true;
        }
    }

	public List<Task> allTasks() {
		return (List<Task>) taskRepo.findAll();
	}

	public List <User> getAllUsers() {
		return (List<User>) userRepo.findAll();
	}

	public void create(Task task) {
		taskRepo.save(task);
		
	}

	public Task getTaskById(Long id) {
		Optional<Task> t = taskRepo.findById(id);
		if(t.isPresent()) {
			return t.get();
		} else {
			return null;
		}
	}

	public void save(Task task) {
		taskRepo.save(task);
		
	}

	public void deleteTaskById(Long id) {
		taskRepo.deleteById(id);
		
	}

	
}

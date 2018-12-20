package com.mattcalimbas.eventplanner.services;

import java.util.List;
import java.util.Optional;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mattcalimbas.eventplanner.models.Event;
import com.mattcalimbas.eventplanner.models.Message;
import com.mattcalimbas.eventplanner.models.User;
import com.mattcalimbas.eventplanner.repositories.EventRepo;
import com.mattcalimbas.eventplanner.repositories.MessageRepo;
import com.mattcalimbas.eventplanner.repositories.UserEventRepo;
import com.mattcalimbas.eventplanner.repositories.UserRepo;

@Service
public class EventPlanService {
	private final UserRepo userRepo;
	private final EventRepo eventRepo;
	private final MessageRepo messageRepo;
	private final UserEventRepo ueRepo;
	
	public EventPlanService(UserRepo userRepo, EventRepo eventRepo, MessageRepo messageRepo, UserEventRepo ueRepo) {
		this.userRepo = userRepo;
		this.eventRepo = eventRepo;
		this.messageRepo = messageRepo;
		this.ueRepo = ueRepo;
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
	public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public User findById1(Long id) {
    	Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
    }
    public List<User> allUsers() {
    	return (List<User>) userRepo.findAll();
    }

    public void update(User user) {
        userRepo.save(user);
    }

    public void create(Event event) {
        eventRepo.save(event);
    }
    public Event findEventById(Long id) {
    	Optional<Event> event = eventRepo.findById(id);
		if(event.isPresent()) {
			return event.get();
		} else {
			return null;
		}
    }
    public List<Event> allEvents() {
    	return (List<Event>) eventRepo.findAll();
    }
    public void update(Event event) {
        eventRepo.save(event);
    }
    
    public void delete(Long id) {
        eventRepo.deleteById(id);
    }
	public void create(Message message) {
		messageRepo.save(message);
		
	}
	public void update(Message message) {
		messageRepo.save(message);
		
	}
	public void save(User user) {
		userRepo.save(user);
		
	}
	public List<Message> allMessages() {
		return (List<Message>) messageRepo.findAll();
	}
	public void save(Event emptyevent) {
		eventRepo.save(emptyevent);
		
	}
	public boolean duplicateUser(String email) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        }
        else {
            return true;
        }
    }
}

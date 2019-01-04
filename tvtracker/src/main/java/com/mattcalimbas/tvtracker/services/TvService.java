package com.mattcalimbas.tvtracker.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mattcalimbas.tvtracker.models.Rating;
import com.mattcalimbas.tvtracker.models.Show;
import com.mattcalimbas.tvtracker.models.User;
import com.mattcalimbas.tvtracker.repositories.RatingRepo;
import com.mattcalimbas.tvtracker.repositories.ShowRepo;
import com.mattcalimbas.tvtracker.repositories.UserRepo;

@Service
public class TvService {
	private final UserRepo userRepo;
	private final ShowRepo showRepo;
	private final RatingRepo ratingRepo;
	
	
	
	public TvService(UserRepo userRepo, ShowRepo showRepo, RatingRepo ratingRepo) {
		this.userRepo = userRepo;
		this.showRepo = showRepo;
		this.ratingRepo = ratingRepo;
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

	public List<Show> allShows() {
		return (List<Show>) showRepo.findAll();
	}

	public void create(Show show) {
		showRepo.save(show);
		
	}

	public Show getShowById(Long id) {
		Optional<Show> s = showRepo.findById(id);
		if(s.isPresent()) {
			return s.get();
		} else {
			return null;
		}
	}
	
	public List<Object[]> showRatingsWithUsersById(Long id) {
		List<Object[]> table = ratingRepo.getShowRatingsWithUsersById(id);
		return table;
	}

	public void save(Rating rating) {
		ratingRepo.save(rating);
		
	}

	public void save(Show show) {
		showRepo.save(show);
		
	}

	public void deleteShowById(Long id) {
		showRepo.deleteById(id);
	}
	
	public List<Object[]> showRatings() {
		List<Object[]> table = showRepo.getShowsAndRatings();
		return table;
	}
}
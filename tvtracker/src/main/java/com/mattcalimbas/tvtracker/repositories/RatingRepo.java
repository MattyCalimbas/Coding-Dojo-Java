package com.mattcalimbas.tvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.tvtracker.models.Rating;

@Repository
public interface RatingRepo extends CrudRepository <Rating, Long> {
	@Query(value="SELECT CONCAT (users.first_name,' ',users.last_name) AS name, ratings.rating FROM ratings LEFT JOIN users ON users.id = ratings.user_id LEFT JOIN shows ON shows.id = ratings.show_id WHERE ratings.show_id= ?1", nativeQuery=true)
	List<Object[]> getShowRatingsWithUsersById(Long id);
	
}

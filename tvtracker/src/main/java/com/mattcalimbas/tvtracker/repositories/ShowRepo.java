package com.mattcalimbas.tvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattcalimbas.tvtracker.models.Show;

@Repository
public interface ShowRepo extends CrudRepository <Show, Long> {
	@Query(value="SELECT shows.tv_name AS name, shows.network, ratings.rating FROM shows LEFT JOIN ratings ON shows.id = ratings.show_id", nativeQuery=true)
	List<Object[]> getShowsAndRatings();
}
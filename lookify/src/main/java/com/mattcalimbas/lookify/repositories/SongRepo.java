package com.mattcalimbas.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mattcalimbas.lookify.models.Song;

public interface SongRepo extends CrudRepository <Song, Long> {
	List<Song> findAll();
	
	List<Song> findByArtistContainsAllIgnoreCase(String artist);
	
	List<Song> findTop10ByOrderByRatingDesc();

}

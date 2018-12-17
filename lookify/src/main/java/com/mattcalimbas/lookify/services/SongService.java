package com.mattcalimbas.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mattcalimbas.lookify.models.Song;
import com.mattcalimbas.lookify.repositories.SongRepo;

@Service
public class SongService {
	private final SongRepo songRepo;
	
	public SongService(SongRepo songRepo) {
		this.songRepo = songRepo;
	}
	public List<Song> allSongs() {
		return songRepo.findAll();
	}
	public Song createSong(Song s) {
		return songRepo.save(s);
	}
	public Song findSong(Long id) {
		Optional<Song> optionalSong = songRepo.findById(id);
		if (optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
	}
	public void updateSong(Song song) {
		songRepo.save(song);
	}
	public void deleteSong(Long id) {
		songRepo.deleteById(id);
	}
	public List<Song> searchByArtist(String artist) {
		return songRepo.findByArtistContainsAllIgnoreCase(artist);
	}
	public List<Song> showTopTen() {
		return songRepo.findTop10ByOrderByRatingDesc();
	}
}

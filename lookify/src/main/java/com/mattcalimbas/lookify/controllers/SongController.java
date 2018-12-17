package com.mattcalimbas.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattcalimbas.lookify.models.Song;
import com.mattcalimbas.lookify.services.SongService;

@Controller
public class SongController {
	private final SongService songService;
	
	public SongController (SongService songService) {
		this.songService = songService;
	}
	
	@RequestMapping("/")
	public String index() {
		return "/index.jsp";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(@ModelAttribute("song") Song song, Model model) {
		List<Song> songs = songService.allSongs();
		model.addAttribute("songs", songs);
		return "/dashboard.jsp";
	}
	
	@RequestMapping("/new")
	public String showNew(@ModelAttribute("song") Song song) {
		return "new.jsp"; 
	}
	
	@PostMapping(value="/new")
	public String newSong (@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";
		} else {
			songService.createSong(song);
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("artist") String artist) {
		return "redirect:/search/"+artist;
		
	}
	
	@RequestMapping("/search/{artist}")
	public String showSearch(Model model, @PathVariable("artist") String artist) {
		List<Song> songs = songService.searchByArtist(artist);
		model.addAttribute("artist", artist);
		model.addAttribute("songs", songs);
		return "result.jsp";
	}
	
	@RequestMapping("/{id}")
	public String showSong (@PathVariable("id")Long id, Model model) {
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "/details.jsp";
	}
	@DeleteMapping(value="/{id}")
	public String destroy(@PathVariable("id")Long id) {
		songService.deleteSong(id);
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/topten")
	public String showTopSongs(Model model) {
		List<Song> songs = songService.showTopTen();
		model.addAttribute("songs", songs);
		return "topten.jsp";
	}
}

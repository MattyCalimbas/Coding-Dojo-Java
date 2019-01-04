package com.mattcalimbas.tvtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattcalimbas.tvtracker.models.Rating;
import com.mattcalimbas.tvtracker.models.Show;
import com.mattcalimbas.tvtracker.models.User;
import com.mattcalimbas.tvtracker.services.TvService;
import com.mattcalimbas.tvtracker.validator.UserValidator;

@Controller
public class MainController {
	private final TvService tvService;
	private final UserValidator userValidator;
	
	
	public MainController(TvService tvService, UserValidator userValidator) {
		this.tvService = tvService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/")
	public String loginRegForm(@ModelAttribute("user") User user, Model model) {
		return "index.jsp";
		
	}
	@PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "index.jsp";
        }
        boolean isDuplicate = tvService.duplicateUser(user.getEmail());
        if(isDuplicate) {
            model.addAttribute("regerror", "Email already in use! Please try again with a different email address!");
            return "index.jsp";
        } else {
            tvService.registerUser(user);
            return "redirect:/";
        }
    }
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		boolean isAuthenticated = tvService.authenticateUser(email, password);
	    if(isAuthenticated) {
	    	User u = tvService.findByEmail(email);
	    	   session.setAttribute("userId", u.getId());
	    	   return "redirect:/shows";
	       } else {
	    	   model.addAttribute("error", "Invalid Credentials. Please try again.");
	    	   return "index.jsp";
	       }
	}
	    
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	    
	}
	@RequestMapping("/shows")
	public String home(HttpSession session, Model model, @Valid @ModelAttribute("show") Show show) {
		Long userId = (Long) session.getAttribute("userId");
		User user = tvService.findUserById(userId);
		model.addAttribute("user", user);
		List<Show> shows = tvService.allShows();
		model.addAttribute("shows", shows);
		return "tvshows.jsp";
	}
	
	@PostMapping("/shows/new")
    public String createShow(@Valid @ModelAttribute("show") Show show, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = tvService.findUserById(userId);
		model.addAttribute("user", user);
		List<Show> shows = tvService.allShows();
		model.addAttribute("shows", shows);
		if(result.hasErrors()) {
            return "tvshows.jsp";
        } else {
            tvService.create(show);
            return "redirect:/shows";
        }
	}
	
	@GetMapping("/shows/{sId}")
	public String showCourse (@PathVariable("sId") Long sId, @Valid @ModelAttribute("newrating") Rating newrating, BindingResult result, Model model, HttpSession session) {
		Show show = tvService.getShowById(sId);
		model.addAttribute("show", show);
		model.addAttribute("table",tvService.showRatingsWithUsersById(sId));
		return "showdetails.jsp";
	}
	
	@PostMapping("/shows/{sId}/addrating")
	public String addMessage (@PathVariable("sId") Long sId, @Valid @ModelAttribute("newrating") Rating newrating, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = tvService.findUserById(userId);
		Show show = tvService.getShowById(sId);
		model.addAttribute("show", show);
		model.addAttribute("table",tvService.showRatingsWithUsersById(sId));
		if(result.hasErrors()) {
			model.addAttribute("error", "Must submit a rating between 1-5");
            return "showdetails.jsp";
		} else {
			newrating.setUser(user);
			newrating.setShow(show);
			tvService.save(newrating);
			return "redirect:/shows/{sId}";
			
		}
	}
	
	@RequestMapping("/shows/{id}/edit")
	public String editShow (@PathVariable("id") Long id, @ModelAttribute("editshow") Show editshow, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Show show= tvService.getShowById(id);
		model.addAttribute("user", userId);
		model.addAttribute("show", show);
		return "editshow.jsp";
	}
	
	@PutMapping("/shows/{id}/edit")
	public String edit (@Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
	    	return "editshow.jsp";
	    } else {
	    	tvService.save(show);
	    	return "redirect:/shows";
	    }
	}
	
	@RequestMapping("/shows/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	tvService.deleteShowById(id);
    	return "redirect:/shows";
	}
	
}

package com.mattcalimbas.eventplanner.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.mattcalimbas.eventplanner.models.Event;
import com.mattcalimbas.eventplanner.models.Message;
import com.mattcalimbas.eventplanner.models.User;
import com.mattcalimbas.eventplanner.services.EventPlanService;
import com.mattcalimbas.eventplanner.validator.UserValidator;

@Controller
public class MainController {
	private EventPlanService ePService;
	private UserValidator uValidator;
	
	public MainController(EventPlanService ePService, UserValidator uValidator) {
		this.ePService = ePService;
		this.uValidator = uValidator;
	}
	private ArrayList<String> states = new ArrayList<String>(Arrays.asList("AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));
	
	@RequestMapping("/")
	public String loginRegForm(@ModelAttribute("register") User user, Model model) {
		model.addAttribute("states", states);
		return "index.jsp";	
	}
	
	@PostMapping("/register")
    public String registration(@Valid @ModelAttribute("register") User user, BindingResult result, Model model) {
        uValidator.validate(user, result);
        model.addAttribute("states", states);
        if(result.hasErrors()) {
            return "index.jsp";
        } 
        boolean isDuplicate = ePService.duplicateUser(user.getEmail());
        if(isDuplicate) {
            model.addAttribute("regerror", "Email already in use! Please try again with a different email address!");
            return "index.jsp";
        }
        else {
            ePService.registerUser(user);
            return "redirect:/";
        }
    }
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("register") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		boolean isAuthenticated = ePService.authenticateUser(email, password);
	    if(isAuthenticated) {
	    	User u = ePService.findByEmail(email);
	    	   session.setAttribute("userId", u.getId());
	    	   return "redirect:/events";
	       } else {
	    	   model.addAttribute("error", "Invalid Credentials. Please try again.");
	    	   return "index.jsp";
	       }
	}
	
	@RequestMapping("/events")
	public String home(HttpSession session, Model model, @ModelAttribute("event") Event event) {
		 Long userId = (Long) session.getAttribute("userId");
	     User user = ePService.findById1(userId);
	     model.addAttribute("user", user);
	     List<Event> events = ePService.allEvents();
	     List<Event> instate = new ArrayList<Event>();
	     List<Event> outofstate = new ArrayList<Event>();
	     model.addAttribute("today", new Date());
	     for(Event party: events) {
	        if(party.getState().equals(user.getState())) {
	        	instate.add(party);
	        } else {
	        	outofstate.add(party);
	        }
	     }
	     model.addAttribute("instate", instate);
	     model.addAttribute("outofstate", outofstate);
	     model.addAttribute("states", states);
	     return "events.jsp";
	}
	
	@PostMapping("/events/create")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
	    User user = ePService.findById1(userId);
	    model.addAttribute("user", user);
	    List<Event> events = ePService.allEvents();
	    List<Event> instate = new ArrayList<Event>();
	    List<Event> outofstate = new ArrayList<Event>();
	    model.addAttribute("today", new Date());
	    for(Event party: events) {
	       if(party.getState().equals(user.getState())) {
	    	   instate.add(party);
	       } else {
	    	   outofstate.add(party);
	       }
	    }
	    model.addAttribute("instate", instate);
	    model.addAttribute("outofstate", outofstate);
	    model.addAttribute("states", states);	
		if(result.hasErrors()) {
			return "events.jsp";
		} else {
			Date today = new Date();
			if(event.getEventDate() == null || event.getEventDate().before(today)) {
				event.setEventDate(today);
			}
	        event.setHost(user);
	        List<User> goers = new ArrayList<User>();
	        goers.add(user);
	        event.setAttendees(goers);
	        ePService.create(event);
	        return "redirect:/events";
		}
	}
	    
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/events/{eId}/join")
	public String join(@PathVariable("eId")Long eId, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		model.addAttribute("user", user);
		Event event = ePService.findEventById(eId);
		List <User> attendees = event.getAttendees();
		attendees.add(user);
		event.setAttendees(attendees);
		ePService.update(user);
		return "redirect:/events";
	}
	
	@RequestMapping("/events/{eId}/cancel")
	public String cancel(@PathVariable("eId") Long eId, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		model.addAttribute("user", user);
		Event event = ePService.findEventById(eId);
		List <User> attendees = event.getAttendees();
		for(int i = 0; i < attendees.size(); i++) {
			if(attendees.get(i).getId() == user.getId()) {
				attendees.remove(i);
			}
		}
		event.setAttendees(attendees);
		ePService.update(user);
		return "redirect:/events";
	}
	
	@RequestMapping("/events/{eId}")
	public String eventDetails(@PathVariable("eId") Long eId, HttpSession session, Model model , @Valid @ModelAttribute("msg") Message message, BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		Event event = ePService.findEventById(eId);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		model.addAttribute("users", event.getAttendees());
		model.addAttribute("messages", event.getMessages());
		return "eventdetails.jsp";
	}
	
	@PostMapping("/events/{eId}/addmessage")
	public String addMessage(@PathVariable("eId") Long eId, @Valid @ModelAttribute("msg") Message message, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		Event event = ePService.findEventById(eId);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		model.addAttribute("users", event.getAttendees());
		List <Message> messages = event.getMessages();
		model.addAttribute("messages", messages);
		if(result.hasErrors()) {
            return "eventdetails.jsp";
		} else {
			ePService.create(message);
			message.setUser(user);
			message.setEvent(event);
			ePService.update(message);
			messages.add(message);
			event.setMessages(messages);
			user.setMessages(messages);
			return "redirect:/events/{eId}";
			
		}
	}
	@RequestMapping("/events/{eId}/edit")
	public String edit(@PathVariable("eId") Long eId, HttpSession session, Model model, @Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		Event event = ePService.findEventById(eId);
		model.addAttribute("event", event);
	    model.addAttribute("user", user);
	    model.addAttribute("states", states);
	    return "eventedit.jsp";
	}

	@PostMapping("/events/{eId}/edit")
	public String update(Model model, @PathVariable("eId") Long eId, @Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = ePService.findById1(userId);
		Event event = ePService.findEventById(eId);
		model.addAttribute("event", event);
	    model.addAttribute("user", user);
	    model.addAttribute("states", states);
	    if(result.hasErrors()) {
	    	return "eventedit.jsp";
	    } else {
	    	event.setHost(user);
	        event.setEventName(emptyevent.getEventName());
	        event.setCity(emptyevent.getCity());
	        event.setState(emptyevent.getState());
	        if(emptyevent.getEventDate() == null) {
	        	event.setEventDate(event.getEventDate());
	        } else {
	        	event.setEventDate(emptyevent.getEventDate());
	        }
	        ePService.save(event);
	        return "redirect:/events";
	    }
	}
	
	@RequestMapping("/events/{eId}/delete")
    public String delete(@PathVariable("eId") Long eId) {
    	Event event = ePService.findEventById(eId);
    	for(User user: event.getAttendees()) {
    		List<Event> myevents= user.getJoinedEvents();
    		myevents.remove(event);
    		user.setJoinedEvents(myevents);
    		ePService.save(user);
    	}
    	for(Message message: ePService.allMessages()) {
    		if(message.getEvent() == event) {
    			ePService.delete(message.getId());
    		}
    	}
    	ePService.delete(eId);
    	return "redirect:/events";
    }
}
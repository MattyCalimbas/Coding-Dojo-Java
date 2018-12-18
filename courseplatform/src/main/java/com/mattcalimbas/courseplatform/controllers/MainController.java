package com.mattcalimbas.courseplatform.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattcalimbas.courseplatform.models.Course;
import com.mattcalimbas.courseplatform.models.User;
import com.mattcalimbas.courseplatform.models.UserCourse;
import com.mattcalimbas.courseplatform.services.CourseService;
import com.mattcalimbas.courseplatform.validator.UserValidator;

@Controller
public class MainController {
	private final CourseService courseService;
	private final UserValidator userValidator;
	
	public MainController(CourseService courseService, UserValidator userValidator) {
		this.courseService = courseService;
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
        boolean isDuplicate = courseService.duplicateUser(user.getEmail());
        if(isDuplicate) {
            model.addAttribute("regerror", "Email already in use! Please try again with a different email address!");
            return "index.jsp";
        } else {
            courseService.registerUser(user);
            return "redirect:/";
        }
    }
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		boolean isAuthenticated = courseService.authenticateUser(email, password);
	    if(isAuthenticated) {
	    	User u = courseService.findByEmail(email);
	    	   session.setAttribute("userId", u.getId());
	    	   return "redirect:/courses";
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
	
	@RequestMapping("/courses")
	public String home(HttpSession session, Model model, @Valid @ModelAttribute("course") Course course) {
		Long userId = (Long) session.getAttribute("userId");
		User user = courseService.findUserById(userId);
		model.addAttribute("user", user);
		List<Course> courses = courseService.allCourses();
		model.addAttribute("courses", courses);
		return "courses.jsp";
	}
	
	@PostMapping("/courses/new")
    public String createCourse(@Valid @ModelAttribute("course") Course course, Model model, BindingResult result) {
        if(result.hasErrors()) {
            return "courses.jsp";
        } else {
            courseService.create(course);
            return "redirect:/courses";
        }
    }
	
	@RequestMapping("/courses/{cId}/join")
	public String addCourse(@PathVariable("cId") Long cid, @ModelAttribute("usercourse") UserCourse usercourse, HttpSession session) {
		Course c = courseService.findCourseById(cid);
		Long userId = (Long) session.getAttribute("userId");
		User u = courseService.findUserById(userId);
		usercourse.setUser(u);
		usercourse.setCourse(c);
		courseService.addUserToCourse(usercourse);
		return "redirect:/courses";
	}
	
	@RequestMapping("/courses/{cId}")
	public String showCourse (@PathVariable("cId") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Course course = courseService.findCourseById(id);
		model.addAttribute("user", userId);
		model.addAttribute("course", course);
		model.addAttribute("students", course.getJoinedCourses());
		return "showcourse.jsp";
	}
	
	@RequestMapping("/courses/{cId}/edit")
	public String editCourse (@PathVariable("cId") Long id, @ModelAttribute("editcourse") Course editcourse, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Course course = courseService.findCourseById(id);
		model.addAttribute("user", userId);
		model.addAttribute("course", course);
		return "editcourse.jsp";
	}
	
	@PutMapping("/courses/{id}/edit")
	public String edit (@Valid @ModelAttribute("course") Course course, BindingResult result) {
		System.out.println("here");
		if(result.hasErrors()) {
	    	return "editcourse.jsp";
	    } else {
	    	courseService.save(course);
	    	return "redirect:/courses";
	    }
	}
	
	@RequestMapping("/courses/{id}/remove")
    public String removeCourse(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User u = courseService.findUserById(userId);
		Course course = courseService.findCourseById(id);
    	List<User> students = course.getJoinedCourses();
        for(int i=0; i<students.size(); i++) {
            if(students.get(i).getId() == u.getId()) {
            	students.remove(i);
            }
        }
        course.setJoinedCourses(students);
        courseService.updateUser(u);
    	return "redirect:/courses";
    }
	
	@RequestMapping("/courses/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	Course course = courseService.findCourseById(id);
    	for(User user: course.getJoinedCourses()) {
    		List<Course> mycourses = user.getCoursesJoined();
    		mycourses.remove(course);
    		user.setCoursesJoined(mycourses);
    		courseService.updateUser(user);
    	}
    	courseService.deleteCourse(id);
    	return "redirect:/courses";
	}
}

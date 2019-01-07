package com.mattcalimbas.taskmanager.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattcalimbas.taskmanager.models.Task;
import com.mattcalimbas.taskmanager.models.User;
import com.mattcalimbas.taskmanager.services.TaskService;
import com.mattcalimbas.taskmanager.validator.UserValidator;

@Controller
public class MainController {
	private final TaskService taskService;
	private final UserValidator userValidator;
	
	public MainController(TaskService taskService, UserValidator userValidator) {
		this.taskService = taskService;
		this.userValidator = userValidator;
	} 
	
	@RequestMapping("/")
	public String loginRegForm(@ModelAttribute("user") User user, Model model) {
		return "logreg.jsp";
		
	}
	@PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "logreg.jsp";
        }
        boolean isDuplicate = taskService.duplicateUser(user.getEmail());
        if(isDuplicate) {
            model.addAttribute("regerror", "Email already in use! Please try again with a different email address!");
            return "logreg.jsp";
        } else {
            taskService.registerUser(user);
            return "redirect:/";
        }
    }
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		boolean isAuthenticated = taskService.authenticateUser(email, password);
	    if(isAuthenticated) {
	    	User u = taskService.findByEmail(email);
	    	   session.setAttribute("userId", u.getId());
	    	   return "redirect:/tasks";
	       } else {
	    	   model.addAttribute("error", "Invalid Credentials. Please try again.");
	    	   return "logreg.jsp";
	       }
	}
	    
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	    
	}
	
	@RequestMapping("/tasks")
	public String dashboard (HttpSession session, Model model, @Valid @ModelAttribute("newtask") Task newtask, BindingResult result, Task task) {
		Long userId = (Long) session.getAttribute("userId");
		User user = taskService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("users", taskService.getAllUsers());
		List<Task> tasks = taskService.allTasks();
		model.addAttribute("tasks", tasks);
		return "dashboard.jsp";
	}
	
	@PostMapping("/tasks/new")
	public String createTask (HttpSession session, Model model, @Valid @ModelAttribute("newtask") Task newtask, BindingResult result, Task task) {
		Long userId = (Long) session.getAttribute("userId");
		User user = taskService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("users", taskService.getAllUsers());
		List<Task> tasks = taskService.allTasks();
		model.addAttribute("tasks", tasks);
		if(result.hasErrors()) {
            return "dashboard.jsp";    
		} else {
			newtask.setCreator(taskService.findUserById(userId));
			task.setAssignee(newtask.getAssignee());
            taskService.create(newtask);
            return "redirect:/tasks";
        }
	}
	
	@RequestMapping("/tasks/{id}")
	public String showCourse (@PathVariable("id") Long id, Model model, HttpSession session) {
		Task task = taskService.getTaskById(id);
		model.addAttribute("task", task);
		return "taskdetails.jsp";
	}
	
	@RequestMapping("/tasks/{id}/edit")
	public String editPage (@Valid @ModelAttribute("task") Task task, BindingResult result, @PathVariable("id") Long id, Model model) {
		model.addAttribute("users", taskService.getAllUsers());
		return "taskedit.jsp";
	}
	@PostMapping("/tasks/{id}/edit")
	public String edit (@Valid @ModelAttribute("task") Task task, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = taskService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("users", taskService.getAllUsers());
		if(result.hasErrors()) {
            return "taskedit.jsp";    
		} else {
			task.setCreator(taskService.findUserById(userId));
			task.setAssignee(task.getAssignee());
            taskService.save(task);
            return "redirect:/tasks";
        }
	}
	@RequestMapping("/tasks/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	taskService.deleteTaskById(id);
    	return "redirect:/tasks";
	}
	
	
}

package com.mattcalimbas.eventplanner.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=1, message="Invalid Character Count for First Name")
	private String firstName;
	@Size(min=1, message="Invalid Character Count for Last Name")
	private String lastName;
	@Pattern(regexp="^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]+$", message="Invalid Email submission.")
    private String email;
	@Size(min=1, message="Invalid City Submission")
	private String city;
	private String state;
    @Size(min=5, message="Invalid Character Count for Password Submission (at least 8 characters).")
	private String password;
    @Transient
    private String passwordConfirmation;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    private List<Event> hosting;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
   		 name="attending",
   		 joinColumns = @JoinColumn(name="user_id"),
   		 inverseJoinColumns = @JoinColumn(name="event_id"))
    private List<Event> joinedEvents;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Message> messages;

	public User() {
	}

	public User(String firstName, String lastName, String email, String city, String state, String password, List<Event> hosting, List<Event> joinedEvents, List<Message> messages) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.state = state;
		this.password = password;
		this.hosting = hosting;
		this.joinedEvents = joinedEvents;
		this.messages = messages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Event> getHosting() {
		return hosting;
	}

	public void setHosting(List<Event> hosting) {
		this.hosting = hosting;
	}

	public List<Event> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
}

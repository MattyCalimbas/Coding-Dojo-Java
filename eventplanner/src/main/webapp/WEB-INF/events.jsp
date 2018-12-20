<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<meta charset="UTF-8">
	<title>Events</title>
</head>
<body>
<div class="container">
      
        <h1 class="mt-5">Welcome, ${user.firstName}</h1> <a href="/logout"><button class='btn btn-success float-right' type='button'>Logout</button></a>
        <br>
        <h4>Here are some of the events in your state:</h4>
        <c:if test="${instate.size() == 0}"><h5> There are currently no events in your area </h5></c:if>
        <c:if test="${instate.size() > 0}">
            <table class="table" border="1">
            <thead class="thead-dark">
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Location</th>
                    <th>Host</th>
                    <th>Action / Status</th>
                </tr>
                <c:forEach items="${instate}" var="event">
                        <tr>
                            <td><a href="/events/${event.id}">${event.eventName}</a></td>
                            <td><fmt:formatDate pattern = "MM/dd/yyyy" value="${event.eventDate}"></fmt:formatDate></td>
                            <td>${event.city}, ${event.state}</td>
                            <td>${event.host.firstName} ${event.host.lastName}</td>
                            <c:choose>
                                <c:when test="${event.host == user}">
                                    <td><a href="/events/${event.id}/edit">Edit</a> | <a href="events/${event.id}/delete">Delete</a></td>
                                </c:when>
                                <c:otherwise>
                                    <c:set var = "attending" value= "${false}"/>
                                    <c:forEach items="${event.getAttendees()}" var="eventgoer">
                                        <c:if test="${eventgoer == user}">
                                            <c:set var = "attending" value= "${true}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${attending == false}">
                                            <td><a href="/events/${event.id}/join">Join</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Joined | <a href="events/${event.id}/bail">Cancel</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>        
                        </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
        <h4>Here are some of the events in other states:</h4>
        <table class="table" border="1">
            <thead class="thead-dark">
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>City</th>
                <th>State</th>
                <th>Host</th>
                <th>Action / Status</th>
            </tr>
            <c:forEach items="${outofstate}" var="event">
                <tr>
                    <td><a href="/events/${event.id}">${event.eventName}</a></td>
                    <td><fmt:formatDate pattern = "MM/dd/yyyy" value="${event.eventDate}"></fmt:formatDate></td>
                    <td>${event.city}</td>
                    <td>${event.state}</td>
                    <td>${event.host.firstName} ${event.host.lastName}</td>
                    <c:choose>
                        <c:when test="${event.host == user}">
                            <td>Joining | <a href="/events/${event.id}/edit">Edit</a> | <a href="events/${event.id}/delete">Delete</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:set var = "attending" value= "${false}"/>
                            <c:forEach items="${event.getAttendees()}" var="eventgoer">
                                <c:if test="${eventgoer == user}">
                                    <c:set var = "attending" value= "${true}"/>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${attending == false}">
                                    <td><a href="/events/${event.id}/join">Join</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td>Joined | <a href="events/${event.id}/cancel">Cancel</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div class="col-md-6">
            <h3>Create an Event</h3>
            <form:form method="POST" action="/events/create" modelAttribute="event">
            <form:errors cssClass="alert alert-danger" path="*" element="p"/>
                <p><form:label path="eventName">Name: </form:label>
                	<form:input path="eventName" cssClass="form-control"/>
                </p>
                <p><form:label path="eventDate"/>Date:
                	<form:input type="date" path="eventDate" cssClass="form-control"/>
                </p>
                <p>Location: 
                    <form:input path="city" cssClass="form-control"/>
                    <form:select path="state" cssClass="form-select">
                        <c:forEach items="${states}" var="st">
                            <form:option path="state" value="${st}"></form:option>
                        </c:forEach>
                    </form:select>
                </p>  
                <input type="submit" value="Create"/>
            </form:form>   
        </div>  
</div>
</body>
</html>
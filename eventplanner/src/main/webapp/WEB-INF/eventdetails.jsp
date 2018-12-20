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
	<title>${event.eventName}</title>
</head>
<body>
<div class="container">
<a href="/events">Back</a>
        <h1>${event.eventName}</h1>
        <div class="col-md-6">
            <h4>Host: ${event.host.firstName} ${event.host.lastName}</h4>
            <h4>Date: <fmt:formatDate pattern = "MM/dd/yyyy" value="${event.eventDate}"></fmt:formatDate></h4>
            <h4>Location: ${event.city}, ${event.state}</h4>
            <h4>Number of People attending: ${event.attendees.size()}</h4><br>
            <table class="table" border="1">
            <thead class="thead-dark">
                <tr>
                    <th>Name</th>
                    <th>Location</th>
                </tr>
                <c:forEach items="${users}" var="goers">
                    <tr>
                        <td>${goers.firstName} ${goers.lastName}</td>
                        <td>${goers.city}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>  
        <div class="col-md-6">
            <h3>Message Wall</h3>
            <div style="height: 150px; width: 500px; overflow: auto; outline: solid 1px black;">
                <c:forEach items="${messages}" var="comment">
                    <p>${comment.user.firstName} says: ${comment.messageContent}</p>
                    <hr>
                </c:forEach>
            </div>
            <br>
            <form:form method="POST" action="/events/${event.id}/addmessage" modelAttribute="msg">
                <form:label path="messageContent"/>Add Comment: 
                <form:input path="messageContent" type="textarea"/>
                <br>
                <input type="submit" value="Submit"/>
            </form:form>
            <br><p><form:errors cssClass="alert alert-danger" path="msg.*" element="p"/></p>
        </div>
    </div>
</body>
</html>
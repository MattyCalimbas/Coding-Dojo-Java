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
<a href="/events">Back</a>
        <h1>${event.eventName}</h1>
        <div class="col-md-6">
            <h3>Edit Event</h3>
            <form:form method="POST" action="/events/${event.id}/edit" modelAttribute="emptyevent">
                <p><form:label path="eventName"/>Name: 
                	<form:input path="eventName" cssClass="form-control"/> Original Event Name: ${event.eventName}</p>
                <p><form:label path="eventDate"/>Date: 
                	<form:input type="date" path="eventDate" cssClass="form-control"/>  Original Date: <fmt:formatDate pattern = "MM/dd/yyyy" value="${event.eventDate}"></fmt:formatDate></p>
                <p><form:label path="city">Location: </form:label>
                    <form:input cssClass="form-control" path="city"/>
                    <form:select path="state">
                        <c:forEach items="${states}" var="st">
                            <form:option path="state" value="${st}"></form:option>
                        </c:forEach>
                    </form:select>
                    Original Location: ${event.city}, ${event.state}
                </p>  
                <input type="submit" value="Finalize Edit"/>
            </form:form>   
        </div>  
        <div class="col-md-6"><p class="text-danger"><form:errors path="emptyevent.*"/></p></div>
</div>
</body>
</html>
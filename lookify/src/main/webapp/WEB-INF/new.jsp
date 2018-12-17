<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<meta charset="UTF-8">
	<title>Add Song</title>
</head>
<body>
<div class ="container">
<a href="/dashboard">Dashboard</a>
<form action="/search" method="post">
			<input type="search" name="artist" placeholder="artist">
			<input type="submit" value="Search Artists">
		</form> 
	<h2 class= 'mt-4'>Add a new song:</h2>
	<form:form action='/new' method='post' modelAttribute="song">
	<form:errors cssStyle="color: #ff0000" path="*"/>
                <div class='row'>
                    <form:label path="title">Title:</form:label>
                    <form:input cssClass="form-control" path="title"/>
                </div>
                
                <div class="row mt-2">
                    <form:label path="artist">Artist:</form:label>
                    <form:input cssClass="form-control" path="artist"/>
                </div>
                <div class="row mt-2">
                    <form:label path="rating">Rating (1-10):</form:label>
                    <form:input type="number" cssClass="form-control" path="rating"/>
                </div>
                <div class = "row mt-3">
                    <button class='btn btn-primary' type='submit'>Create</button>
                </div>          
	</form:form>
</div>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<meta charset="UTF-8">
	<title>Search</title>
</head>
<body>
<div class="container">
<a href="/dashboard">Dashboard</a>
<form action="/search" method="post">
			<input type="search" name="artist" placeholder="artist">
			<input type="submit" value="New Search">
		</form> 
<h1 class="mt-4">Songs by artist: <c:out value="${artist}"/></h1>
		<table class="table" border="1">
			<thead class="thead-dark">
			<tr>
				<th>Title</th>
				<th>Rating</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${songs}" var="song">
    			<tr>    
   				<td><a href="/${song.id}"><c:out value="${song.title}"/></a></td>
    				<td><c:out value="${song.rating}"/></td>
    			<td><form action="/${song.id}" method="post">
    				<input type="hidden" name="_method" value="delete">
    				<input type="submit" value="Delete">
				</form></td>
    			</tr>
			</c:forEach>
		</table>
</div>
</body>
</html>
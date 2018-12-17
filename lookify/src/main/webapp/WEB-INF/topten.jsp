<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<meta charset="UTF-8">
	<title>Search</title>
</head>
<body>
<div class="container">
<a href="/dashboard">Dashboard</a>
<form action="/search" method="post">
			<input type="search" name="artist" placeholder="artist">
			<input type="submit" value="Search Artists">
		</form> 
<h2 class= 'mt-4'>Top Ten Songs:</h2>
<table class= "table" border="1">
    <thead class="thead-dark">
        <tr>
            <th>Rating</th>
            <th>Title</th>
            <th>Artist</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${songs}" var="song">
        <tr>
            <td><c:out value="${song.rating}"/></td>
            <td><a href="/${song.id}"><c:out value="${song.title}"/></a></td>
            <td><c:out value="${song.artist}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
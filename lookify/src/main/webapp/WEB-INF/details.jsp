<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<meta charset="UTF-8">
	<title>Details</title>
</head>
<body>
<div class="container">
<a href="/dashboard">Dashboard</a>
<form action="/search" method="post">
			<input type="search" name="artist" placeholder="artist">
			<input type="submit" value="Search Artists">
		</form> 
<h3 class="mt-5 mb-2">Song Details</h3>
		<p class="font-weight-bold">Title: <c:out value="${song.title}"/></p>
		<p>Artist: <c:out value="${song.artist}"/></p>
		<p>Rating: <c:out value="${song.rating}"/></p>
		
	<form action="/${song.id}" method="post">
    	<input type="hidden" name="_method" value="delete">
    	<input type="submit" value="Delete">
	</form>

</div>
</body>
</html>
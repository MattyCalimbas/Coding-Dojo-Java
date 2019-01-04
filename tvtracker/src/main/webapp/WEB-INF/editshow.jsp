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
	<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Edit <c:out value="${show.tvName}"/></h1>
			<form:form method="post" action="/shows/${show.id}/edit" modelAttribute="show">
			<input type="hidden" name="_method" value="put">
				<p>
					<form:label path="tvName">Name:</form:label>
					<form:input cssClass="form-control" type="text" path="tvName"/>
				</p>
				<p>
					<form:label path="network">Network:</form:label>
					<form:input cssClass="form-control" type="text" path="network"/>
				</p>
				<button type="submit">Submit</button>
			</form:form>
			<form:errors cssClass="text-danger" path="show.*"/>
			
			<a href="/shows/${show.id}/delete"><button class="mt-3">Delete</button></a>
</div>
</body>
</html>
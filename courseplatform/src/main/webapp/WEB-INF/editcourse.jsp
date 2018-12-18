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
<h1>Edit <c:out value="${course.name}"/></h1>
			<form:form method="post" action="/courses/${course.id}/edit" modelAttribute="course">
			<input type="hidden" name="_method" value="put">
				<p>
					<form:label path="name">Name:</form:label>
					<form:input cssClass="form-control" type="text" path="name"/>
				</p>
				<p>
					<form:label path="instructor">Instructor:</form:label>
					<form:input cssClass="form-control" type="text" path="instructor"/>
				</p>
				<p>
					<form:label path="capacity">Capacity:</form:label>
					<form:input cssClass="form-control" path="capacity"/>
				</p>
				<button type="submit">Submit</button>
			</form:form>
			<form:errors cssClass="text-danger" path="course.*"/>
		</div>
</body>
</html>
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
<h1 class="mt-5">Welcome, ${user.firstName}</h1> <a href="/logout"><button class='btn btn-success float-right' type='button'>Logout</button></a>
        <br>
        <h4>Courses:</h4>
            <table class="table" border="1">
            <thead class="thead-dark">
                <tr>
                    <th>Course</th>
                    <th>Instructor</th>
                    <th>Capacity</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${courses}" var="course">
                        <tr>
                            <td><a href="/courses/${course.id}">${course.name}</a></td>
                            <td>${course.instructor}</td>
                            <td>${course.capacity}</td>
                            <c:choose>
                                <c:when test="${course.capacity == course.joinedCourses.size()}">
                                    <td>Full</td>
                                </c:when>
                                <c:otherwise>
                                   <c:set var="attending" value="${false}"/>
                                   <c:forEach items="${course.getJoinedCourses()}" var="student">
                                   		<c:if  test="${student == user }">
                                   			<c:set var="attending" value="${true}"/>
                                   		</c:if>
                                   </c:forEach>
                                   <c:choose>
                                   		<c:when test="${attending == false}">
                                   			<td><a href="/courses/${course.id}/join">Add</a></td>
                                   		</c:when>
                                   		<c:otherwise>
                                   			<td>Already Added</td>
                                   		</c:otherwise>
                                   	</c:choose>
                                </c:otherwise>
                            </c:choose>        
                        </tr>
                </c:forEach>
            </table>
		<div class="col-md-6 mt-5">
            <h3>Create a New Course:</h3>
            <form:form method="POST" action="/courses/new" modelAttribute="course">
                <p>
                	<form:label path="name">Name: </form:label>
                	<form:input path="name" cssClass="form-control"/>
                </p>
                
                <p>
                	<form:label path="instructor"/>Instructor:
                	<form:input path="instructor" cssClass="form-control"/>
                </p>
             
                <p>
                	<form:label path="capacity"/>Capacity:
                	<form:input type="number" path="capacity" value="1" cssClass="form-control"/>
                </p>
             
                <input type="submit" value="Create"/>
            </form:form>   
       </div>  
     <div class="col-md-6"><p><form:errors path="*"/></p></div>

</div>
</body>
</html>
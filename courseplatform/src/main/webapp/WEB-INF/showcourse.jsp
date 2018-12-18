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
	<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1 class="mt-4"><c:out value="${course.name}"/></h1>
	<p>
	<h4>Instructor: <c:out value="${course.instructor}"/></h4>
	<p>
	<p class="font-weight-bold">Current Class Population: <c:out value="${course.joinedCourses.size()}"/>
	 <table class="table" border="1">
            <thead class="thead-dark">
				<tr>
    				<th>Name</th>
				    <th>Sign Up Date</th>
				    <th>Action</th>
  				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
  				<tr>
				    <td><c:out value="${student.firstName} ${student.lastName}"/></td>
				    <td><fmt:formatDate pattern ="MMMM dd, yyyy" value ="${student.createdAt}"/></td>
					<c:choose>
    					<c:when test="${student.id == user}">
        					<td><a href="/courses/${course.id}/remove">Remove</a></td> 
        		    	</c:when>    
    					<c:otherwise>
        					<td></td> 
    					</c:otherwise>
					</c:choose>
                </tr>
    			</c:forEach>
   			</tbody>
  		</table>
  		<c:forEach items="${students}" var="student">
  			<c:choose>
  				<c:when test="${student.id == user}"> <a href="/courses/${course.id}/edit"><button>Edit</button></a> <a href="/courses/${course.id}/delete"><button>Delete</button></a> </c:when>
			</c:choose>
		</c:forEach>
	</div>
</body>
</html>
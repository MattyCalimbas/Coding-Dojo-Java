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
        <h4>Tv Shows:</h4>
            <table class="table" border="1">
            <thead class="thead-dark">
                <tr>
                    <th>Show</th>
                    <th>Network</th>
                </tr>
                <c:forEach items="${shows}" var="show">
                        <tr>
                            <td><a href="/shows/${show.id}">${show.tvName}</a></td>
                            <td>${show.network}</td>
                        </tr>
                </c:forEach>
            </table>
		<div class="col-md-6 mt-5">
            <h3>Create a New Show:</h3>
            <form:form method="POST" action="/shows/new" modelAttribute="show">
                <p>
                	<form:label path="tvName">Name: </form:label>
                	<form:input path="tvName" cssClass="form-control"/>
                </p>
                
                <p>
                	<form:label path="network"/>Network:
                	<form:input path="network" cssClass="form-control"/>
                </p>
             
                <input type="submit" value="Create"/>
            </form:form>   
       </div>  
     <div class="col-md-6"><p class="text-danger"><form:errors path="show.*"/></p></div>
</div>
</body>
</html>
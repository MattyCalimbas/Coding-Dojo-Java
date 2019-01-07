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
	<title>Task Manager</title>
</head>
<body>
<div class="container">
<h1 class="mt-5">Welcome, ${user.firstName} ${user.lastName}</h1> <a href="/logout"><button class='btn btn-success float-right' type='button'>Logout</button></a>
        <br>
        <h4>Live Tasks:</h4>
            <table class="table" border="1">
            <thead class="thead-dark">
                <tr>
                    <th>Task</th>
                    <th>Creator</th>
                    <th>Assignee</th>
                    <th>Priority</th>
                </tr>
                <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td><a href="/tasks/${task.id}">${task.taskName}</a></td>
                            <td>${task.creator.firstName} ${task.creator.lastName }</td>
                            <td>${task.assignee.firstName} ${task.assignee.lastName }</td>
                            <td>${task.priority}</td>
                        </tr>
                </c:forEach>
            </table>
		<div class="col-md-6 mt-5">
            <h3>Create a New Task:</h3>
            <form:form method="POST" action="/tasks/new" modelAttribute="newtask">
                <p>
                	<form:label path="taskName">Name: </form:label>
                	<form:input path="taskName" cssClass="form-control"/>
                </p>
                
                <p>
                	<form:label path="assignee">Assignee:</form:label>
                    <form:select cssClass="form-control" path="assignee">
                    	<c:forEach items="${users}" var="u">
                    		<form:option value="${u.id}"><c:out value="${u.firstName} ${u.lastName}"/></form:option>
                    	</c:forEach>
                    </form:select>
                </p>
                
                <p>
                	<form:label path="priority">Priority:</form:label>
                    <form:select cssClass="form-control" path="priority">
                    	
                    		<form:option value="low">Low</form:option>
                    		<form:option value="medium">Medium</form:option>
                    		<form:option value="high">High</form:option>

                    </form:select>
                </p>
             
                <input type="submit" value="Create"/>
            </form:form>   
       </div>  
     <div class="col-md-6"><form:errors cssStyle="color: #ff0000" path="newtask.*"/><p class="text-danger"><c:out value="${taskerror}"/></p></div>
</div>
</body>
</html>
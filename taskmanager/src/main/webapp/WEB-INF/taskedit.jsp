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
 <h3>Edit Task:</h3>
            <form:form method="POST" action="/tasks/${task.id}/edit" modelAttribute="task">
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
             
                <input type="submit" value="Save Edit"/>
            </form:form>
            <form:errors cssStyle="color: #ff0000" path="task.*"/><p class="text-danger"><c:out value="${error}"/></p>
</div>
</body>
</html>
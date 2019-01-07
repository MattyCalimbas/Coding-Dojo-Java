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
<h1 class="mt-5">${event.eventName}</h1>
        <div class="col-md-6">
            <h2>Task: ${task.taskName}</h2>
            <h4>Assignee: ${task.assignee.firstName} ${task.assignee.lastName}</h4>
            <h4 class="mb-3">Priority: ${task.priority}</h4>
          </div> 
          <a href="/tasks/${task.id}/edit"><button>Edit</button></a> <a href="/tasks/${task.id}/delete"><button>Delete</button></a>
          
</div>
</body>
</html>
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

<div class="py-5 text-center">
		<h1 class='mt-4'>Welcome to Courses</h1>
		<p class="lead">Your personalized course planner.</p>
	</div>
       <div class="row">     
            <div class="col-md-8">
                <h3 class='mb-2'>Registration:</h3>

                <form:form action='/register' method='post' modelAttribute="user">
                <form:errors cssStyle="color: #ff0000" path="*"/>
                <p class="text-danger"><c:out value="${regerror}"/></p>
                    <div class="row">
                        <div class='col-md-6 mb-2'>
                            <form:label path='firstName'/>First Name:
                            <form:input cssClass='form-control' path='firstName'/>
                        </div>

                        <div class='col-md-6 mb-2'>
                            <form:label path='lastName'/>Last Name:
                            <form:input cssClass='form-control' path='lastName'/>
                        </div>
                    </div>
                    
                    <div class='mb-3'>
                        <form:label path="email"/>Email
                        <form:input type="email" cssClass='form-control' path='email'/>
                    </div>

                    <div class='mb-3'>
                        <form:label path="password"/>Password
                        <form:input type="password" cssClass='form-control' path='password' placeholder='At Least 8 Characters Long'/>
                    </div>

                    <div class='mb-3'>
                        <form:label path="passwordConfirmation"/>Confirm
                        <form:input type="password" cssClass='form-control' path='passwordConfirmation' placeholder='Confirm Password'/>
                    </div>
                    
                    <button class='btn btn-primary btn-lg' type='submit'>Submit</button>
                
                </form:form>
            </div>

            <div class="col-md-4 mb-2">
                <h3 class='mb-2'>Login:</h3>
                <p class="text-danger"><c:out value="${error}"/></p>
                <form action='/login' method='post'>
                    <div class='mb-2'>
                        <label for="email">Email</label>
                        <input type="text" class='form-control' id="email" name='email'>
                    </div>    
                    
                    <div class='mb-3'>
                        <label for="password">Password</label>
                        <input type="password" class='form-control' id="password" name='password' placeholder='Password'>
                    </div>
                    <button class='btn btn-success' type='submit'>Login</button>
                </form>
            </div>
        </div>

</div>
</body>
</html>
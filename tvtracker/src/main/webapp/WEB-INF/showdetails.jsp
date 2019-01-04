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
<h1 class="mt-4"><c:out value="${show.tvName}"/></h1>
	<p>
	<h4>Network: <c:out value="${show.network}"/></h4>
	<p>
	 <table class="table" border="1">
            <thead class="thead-dark">
				<tr>
    				<th>Name</th>
				    <th>Rating</th>
  				</tr>
			</thead>
			<tbody>
				<c:forEach items="${table}" var="row">
                	<tr style="padding: 5px; text-align: left;">
                <c:forEach items="${row}" var="data">
                    <td style="padding: 5px; text-align: left; border: 1px solid black;">${data}</td>
				</c:forEach>
                </tr>
            </c:forEach>
   			</tbody>
  		</table>
  		<a href="/shows/${show.id}/edit"><button class="button mb-5">Edit</button></a>
  		
  		<br>
  		<form method="post" action="/shows/${show.id}/addrating">
        <p>
            <label for="rating">Add a Rating!</label>
            <input type="number" step="0.5" id="rating" name="rating"/>
        </p>
        <input type="submit" value="Add a Rating"/>
    	</form>    
            <br><p class="text-danger"><c:out value="${error}" /></p>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Main page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<header class="header">
	<div class="container">
		<div class="header_inner">
			<a class="header_logo"href="${request.contextPath}controller?command=showMainPage">OnlineTraining</a>
			
			<nav class="nav"> 
				<c:if test="${user.role == 'ADMIN'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showMyCourses">Edit Courses</a>
				</c:if>
				<c:if test="${user.role == 'STUDENT'}">
				    <a class= "nav_link" href="${request.contextPath}controller?command=showMyCourses">My Courses</a>
				</c:if>
				<c:out value="Hello, ${user.firstName} ${user.lastName}"/>
				<a class= "nav_link" href="${request.contextPath}controller?command=logout"">Log out</a>
				<a class= "nav_link" href="#">en</a>
			</nav>
		</div> 
	</div>
</header>

<div class="intro">
	<div class="container">
		<div class="intro_inner">
			<h1 class="intro_title">Welcome to Online_Training</h1>
		</div>
	</div>
</div>

<section class="section">
	<div class="container">
		<div class="section_header">
			<h2 class="section_title">Courses</h2>
		</div>
	</div>
</section>

<table>
	<tr>
		<th>Course</th>
		<th class="description">Description</th>
		<th>From</th>
		<th>To</th>
		<th>Teacher first name</th>
		<th>Teacher last name</th>
		<th></th>
	</tr>

	<c:forEach  items="${courses}" var="course">
    <tr>
		<td><c:out value="${course.name}"/></td>
		<td><c:out value="${course.description}"/></td>
		<td><c:out value="${course.dateFrom}"/></td>
		<td><c:out value="${course.dateTo}"/></td>
		<td><c:out value="${course.teacherFirstName}"/></td>
		<td><c:out value="${course.teacherLastName}"/></td>
		<td class="button">
			<c:if test="${user.role == 'STUDENT'}">
			<form action="controller" method= "post">
			    <input type="hidden" name="course_id"  value="${course.id}"/>
			    <input type="hidden" name="command" value="enrollCourse">
				<input type="submit" value="Enroll"/>
			</form>
			</c:if >
			<c:if test="${user.role == 'ADMIN'}">
			<form action="controller" method= "post">
			    <input type="hidden" name="course_id"  value="${course.id}"/>
			    <input type="hidden" name="command" value="editCourse">
				<input type="submit" value="Edit"/>
			</form>
			</c:if >
		</td>
	</tr>
    </c:forEach>
</table>

<footer>
	
	
	<span class="center" All rights reserveds &copy; 2020 span/>
</footer>
</body>


</html>
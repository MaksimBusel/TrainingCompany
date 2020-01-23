<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>MyCourses page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<header class="header">
	<div class="container">
		<div class="header_inner">
			<a class="header_logo"href="${request.contextPath}controller?command=mainPage">OnlineTraining</a>
			
			<nav class="nav"> 
				<a class= "nav_link" href="${request.contextPath}controller?command=myCourses">My Courses</a>
				<a class= "nav_link" href="#">My Tasks</a>
				<a class= "nav_link" href="#">en</a>
				<a class= "nav_link" href="${request.contextPath}controller?command=logout"">Log out</a>
			</nav>
		</div> 
	</div>
</header>

<div class="intro">
	<div class="container">
		<div class="intro_inner">
			<h1 class="intro_title"></h1>
		</div>
	</div>
</div>

<section class="section">
	<div class="container">
		<div class="section_header">
			<h2 class="section_title">My Courses</h2>
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

	<c:forEach  items="${myCourses}" var="course">
    <tr>
		<td><a href="${request.contextPath}controller?command=showCourseTasks&course_id=${course.id}">${course.name}</a></td>
		<td><c:out value="${course.description}"/></td>
		<td><c:out value="${course.dateFrom}"/></td>
		<td><c:out value="${course.dateTo}"/></td>
		<td><c:out value="${course.teacherFirstName}"/></td>
		<td><c:out value="${course.teacherLastName}"/></td>
		<td class="button">
			<form action="controller" method= "post">
			    <input type="hidden" name="course_id"  value="${course.id}"/>
            	<input type="hidden" name="command" value="unenrollCourse">
				<input type="submit" value="Unenroll"/>
			</form>
		</td>
	</tr>
    </c:forEach>
</table>


<footer>
	<! span class="center" All rights reserveds &copy; 2020span >
</footer>

</body>
</html>
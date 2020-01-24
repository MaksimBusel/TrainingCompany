<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Tasks page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<header class="header">
	<div class="container">
		<div class="header_inner">
			<a class="header_logo"href="${request.contextPath}controller?command=showMainPage">OnlineTraining</a>
			
			<nav class="nav"> 
				<a class= "nav_link" href="${request.contextPath}controller?command=showMyCourses">My Courses</a>
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
			<h2 class="section_title">Tasks</h2>
		</div>
	</div>
</section>

<table>
	<tr>
		<th>Task</th>
		<th>Start Date</th>
		<th>Deadline</th>
		<th></th>
	</tr>

	<c:forEach  items="${tasks}" var="task">
    <tr>
		<td><c:out value="${task.name}"/></td>
		<td><c:out value="${task.dateFrom}"/></td>
		<td><c:out value="${task.dateTo}"/></td>
		<td class="button">
			<form action="controller" method= "post" enctype="multipart/form-data">
				<input type="file" name="Add task"/>
			</form>
		</td>
	</tr>
    </c:forEach>
</table>

<div><a class= "nav_link" href="${request.contextPath}controller?command=showMyMarks&courseId=${course_id}">My marks</a></div>

<footer>
	<! span class="center" All rights reserveds &copy; 2020span >
</footer>
	
</body>
</html>
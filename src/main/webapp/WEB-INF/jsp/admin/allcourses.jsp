<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Courses page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title">All Courses</h2>
			</div>
		</div>
	</section>

	<table>
		<tr>
			<th>Course</th>
			<th>Description</th>
			<th>Date from</th>
			<th>Date to</th>
			<th>Teacher first name</th>
			<th>Teacher last name</th>
			<th></th>
		</tr>

		<c:forEach  items="${courses}" var="course">
		<tr>			
			<td><c:out value="${course.name}"/></td>
			<td class="description"><c:out value="${course.description}"/></td>
			<td><c:out value="${course.dateFrom}"/></td>
			<td><c:out value="${course.dateTo}"/></td>
			<td><c:out value="${course.teacherFirstName}"/></td>
			<td><c:out value="${course.teacherLastName}"/></td>
			<td>
				<form action="controller" method= "post">
					<input type="hidden" name="course_id"  value="${course.id}"/>
					<input type="hidden" name="command" value="manageTasks">
					<input type="submit" value="Manage tasks"/>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>

</body>


</html>
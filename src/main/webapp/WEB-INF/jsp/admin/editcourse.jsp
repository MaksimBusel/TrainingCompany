<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Edit Course</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title">Edit Course</h2>
			</div>
		</div>
	</section>

	<table>
		<tr>
			<th>Course</th>
			<th>Description</th>
			<th>From</th>
			<th>To</th>
			<th>Select teacher</th>
			<th></th>
		</tr>

		<c:forEach items="${courses}" var="course">
		<tr>
		<form action="controller" method= "post">
			<td>
				<input type="text" name="course_name" value="${course.name}">
			</td>
			<td class="description">
				<textarea name="description" cols="80" rows="3">${course.description}</textarea>
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_from" value="${course.dateFrom}">
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_to" value="${course.dateFrom}">
			</td>
			<td>
				<select name="teacher">
					<option value="${course.teacherId}">${course.teacherFirstName} ${course.teacherLastName}</option>
					<c:forEach items="${teachers}" var="teacher">
						<option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
					</c:forEach>
				</select>
			</td>
			<td class="button">
				<input type="hidden" name="course_id" value="${course.id}">
				<input type="hidden" name="command" value="editCourse">
				<input type="submit" value="Edit"/>
		</form>
				<form action="controller" method= "post">
					<input type="hidden" name="course_id" value="${course.id}">
					<input type="hidden" name="command" value="lockCourse">
					<input type="submit" value="Delete"/>
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
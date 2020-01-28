<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Add Task</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title">Add Task</h2>
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

		<tr>
		<form action="controller" method= "post">
			<td>
				<input type="text" name="task_name">
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_from">
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_to">
			</td>
			<td class="button">
				<input type="hidden" name="course_id" value="${courseId}">
				<input type="hidden" name="command" value="addTask">
				<input type="submit" value="Add"/>
			</td>
		</form>
		</tr>
	</table>
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>

</body>


</html>
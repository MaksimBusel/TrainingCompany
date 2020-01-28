<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Tasks page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
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
		<td>
			<form action="controller" method= "post" enctype="multipart/form-data">
				<input type="file" name="student_task"/>
				<input type="hidden" name="task_id"  value="${task.id}"/>
				<input type="hidden" name="command" value="uploadStudentTask">
				<input type="submit" value="Upload"/>
			</form>
		</td>
	</tr>
    </c:forEach>
</table>
	<form action="controller" method= "post">
		<input type="hidden" name="courseId"  value="${courseId}"/>
        <input type="hidden" name="command" value="showMyMarks">
		<input type="submit" value="My marks"/>
	</form>
	
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>
</body>

</html>
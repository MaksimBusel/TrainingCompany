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
			<h2 class="section_title">Grades of students who have this task</h2>
		</div>
	</div>
</section>

<table>
	<tr>
		<th>Student</th>
		<th>Mark</th>
		<th>Feedback</th>
		<th></th>
	</tr>

	<c:forEach  items="${students}" var="student">
    <tr>
		<td><c:out value="${student.studentFirstName} ${student.studentLastName}"/></td>
		<form action="controller" method= "post">
		<td>
			<select name="mark">
				<option value="${student.mark}">${student.mark}</option>
				<option value="0"></option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>  /10
		</td>
		<td>
			<textarea name="feedback" cols="120" rows="3">${student.feedback}</textarea>
		</td>
		<td>
			<input type="hidden" name="task_id" value="${student.taskId}">
			<input type="hidden" name="student_task_id" value="${student.id}">
			<input type="hidden" name="command" value="estimateTask">
			<input type="submit" value="Estimate"/>
		</td>
		</form>
	</tr>
    </c:forEach>
</table>
	
	
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>
</body>

</html>
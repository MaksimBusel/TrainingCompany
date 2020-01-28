<DOCTYPE HTML>

<html>

<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	
		<div class="header_inner">
			<a class="header_logo"href="${request.contextPath}controller?command=showMainPage">OnlineTraining</a>
			
			<nav class="nav"> 
				<c:out value="Hello, ${user.firstName} ${user.lastName}"/>
				<a class= "nav_link" href="${request.contextPath}controller?command=logout"">Log out</a>
				<a class= "nav_link" href="#">en</a>
			</nav>
		</div> 
		<div class="menu">
				<c:if test="${user.role == 'ADMIN'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showEditCoursePage">Edit course</a>
				</c:if>
				<c:if test="${user.role == 'ADMIN'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showAddCoursePage">Add course</a>
				</c:if>
				<c:if test="${user.role == 'TEACHER'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showTeacherCourses">My courses</a>
				</c:if>
				<c:if test="${user.role == 'STUDENT'}">
				    <a class= "nav_link" href="${request.contextPath}controller?command=showMyCourses">My Courses</a>
				</c:if>
			</div>
	
</body>

</html>
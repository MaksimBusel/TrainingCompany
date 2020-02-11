<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Error Page</title>
		<link rel="stylesheet" href="css/style.css"/>
	</head>

	<body>
		<div class="error_container">
			<div class="error_page">
				<div class="title_error"><fmt:message key="error.error"/></div>
				<div class="message_error">
					<p>${error}</p>
				</div>
			</div>
		</div>
	</body>

</html>


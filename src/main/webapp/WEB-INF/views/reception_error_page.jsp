<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Error occurred! Please enter the correct details</h2>
<p> ${message}</p>
<a href="${pageContext.request.contextPath}/reception/add-patient">Retry</a>
</body>
</html>
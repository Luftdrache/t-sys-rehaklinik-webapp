<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 21.07.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Error occurred! Please enter the correct details</h2>
<p> ${message}</p>
<a href="${pageContext.request.contextPath}/admin/add-employee">Retry</a>
</body>
</html>

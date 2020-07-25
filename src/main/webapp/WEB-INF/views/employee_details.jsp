<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 25.07.2020
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png"
          type="image/png">
    <title>MedHelper</title>
</head>
<body>
Employee info:
<h4>${message}</h4>
<h4>${employee.firstName}</h4>
<h4>${employee.middleName}</h4>
<h4>${employee.surname}</h4>
<h4>${employee.dateOfBirth}</h4>
<h4>${employee.address}</h4>
<h4>${employee.passportId}</h4>
<h4>${employee.phoneNumber}</h4>
<h4>${employee.email}</h4>
<h4>${employee.position}</h4>
<h4>${employee.qualificationCategory}</h4>
<h4>${employee.role}</h4>
</body>
</html>

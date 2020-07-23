<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 13.07.2020
  Time: 12:38
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

    <style>
        body {
            background-color: #1d3d4e;
            color: #c9e9ff;
        }

        p {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<h1>MedHelper</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1" style="background-color: orange"></div>
        <div class="col-md-11" style="background-color: yellowgreen">
            <p class="font-italic"> Healing Hands. Caring Hearts. </p>
        </div>
    </div>
</div>
<div>
    <div class="container-fluid">
        <div content="container" class="col-sm-10 col-sm-offset-2" style="background-color: #c9e9ff">
            <p>Employees: </p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Position</th>
                    <th scope="col">Category</th>
                    <th scope="col">Address</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Email</th>
                    <th scope="col">Role</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody class="table table-hover">
                <c:forEach items="${allEmployees}" var="empl">
                    <tr>
                        <th>${empl.employeeId}</th>
                        <th>${empl.name}</th>
                        <td>${empl.surname}</td>
                        <td>${empl.position}</td>
                        <td>${empl.qualificationCategory}</td>
                        <td>${empl.address}</td>
                        <td>${empl.phone}</td>
                        <td>${empl.email}</td>
                        <td>${empl.role}</td>
                        <td>Buttons</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div content="container" class="col-sm-10 col-sm-offset-2">
            <p>${messageAboutEmployees}</p>
        </div>
        <footer class="page-footer font-small mdb-color pt-4">
            <div class="col-md-7 col-lg-8">
                <p class="text-center text-md-left">
                    <strong>© 2020 Julia Dalskaya</strong>
                </p>
            </div>
        </footer>
    </div>
</div>
</body>
</html>
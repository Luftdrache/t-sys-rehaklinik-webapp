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
<div content="container-fluid" class="col-sm-10 col-sm-offset-2" style="background-color: #c9e9ff">
    <nav class="navbar navbar-expand-lg navbar-dark blue lighten-2 mb-4">
        <div class="col-sm-4 col-sm-offset-8" id="navbarSupportedContent">
            <form class="form-inline mr-auto" action="/admin/find-employee-by-id/{id}" method="get">
                <input class="form-control" type="text" placeholder="Search" aria-label="Search" name="id">
                <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2" style="background-color: orange"
                        type="submit">Search
                </button>
            </form>

        </div>
    </nav>
</div>
<div>
    <div content="container-fluid" class="col-sm-10 col-sm-offset-2" style="listPatientsbackground-color: #c9e9ff">
        <p>Patients: </p>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Gender</th>
                <th scope="col">Date of Birth</th>
                <th scope="col">Insurance Company</th>
                <th scope="col">Insurance Policy Code</th>
                <th scope="col">Attending Doctor</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody class="table table-hover">
            <c:forEach items="${listPatients}" var="patient">
                <tr>
                    <th>${patient.patientId}</th>
                    <th>${patient.name}</th>
                    <td>${patient.surname}</td>
                    <td>${patient.gender}</td>
                    <td>${patient.dateOfBirth}</td>
                    <td>${patient.insuranceCompany}</td>
                    <td>${patient.insurancePolicyCode}</td>
                    <td>${patient.attendingDoctor}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/reception/patient-details/${patient.patientId}" method="get">
                            <input type="submit" class="btn btn-primary btn-sm" value="Profile">
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/edit/${empl.employeeId}" method="get">
                                <%--                            <input type="hidden" name="employeeIdToEdit" value="${empl.employeeId}">--%>
                            <input type="submit" class="btn btn-primary btn-sm" value="Edit">
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/delete-employee" method="post">
                            <input type="hidden" name="employeeIdToDelete" value="${empl.employeeId}">
                            <input type="submit" class="btn btn-primary btn-sm" value="Delete">
                        </form>
                    </td>
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
</body>
</html>

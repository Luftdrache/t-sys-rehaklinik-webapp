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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-11" style="padding-left: 1%; color: #c9e9ff">
            <h1>MedHelper</h1>
        </div>
        <div class="col-md-1">
            <h1><input type="button" value="Sign out" class="btn login_btn"
                       style="background-color: orange;"></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-1" style="background-color: orange"></div>
        <div class="col-md-11" style="background-color: yellowgreen">
            <p class="font-italic" style="color: white; font-size: small"> Healing Hands. Caring Hearts. </p>
        </div>
    </div>
    <div style="height: 10px"></div>

    <div class="col-sm-10 col-sm-offset-2"
         style="background-color: #c9e9ff; color: #1d3d4e; height: 70%; border-radius: 20px">
        <h3>Patients: </h3>
        <table class="table table-condensed" style="font-size: small">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Gender</th>
                <th scope="col">Age</th>
                <th scope="col">Phone</th>
                <th scope="col">Insurance Company</th>
                <th scope="col">Insurance Policy</th>
                <th scope="col">Attending Doctor</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody class="table table-hover">
            <c:forEach items="${allPatients}" var="pat">
                <tr>
                    <td>${pat.patientId}</td>
                    <td>${pat.name}</td>
                    <td>${pat.gender}</td>
                    <td>${pat.age}</td>
                    <td>${pat.phone}</td>
                    <td>${pat.insuranceCompany}</td>
                    <td>${pat.insurancePolicyCode}</td>
                    <td>${pat.attendingDoctor}</td>
                    <td class="text-right row padding-right: 5px">
                        <form action="${pageContext.request.contextPath}/reception/patient-details/${pat.patientId}" method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Profile"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-eye fa-2x"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/reception/edit-patient-data/${pat.patientId}" method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-edit fa-2x"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/reception/delete-patient" method="post">
                            <input type="hidden" name="patientIdToDelete" value="${pat.patientId}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                    style="background-color: yellowgreen">
                                <i class="fa fa-trash fa-2x"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${message}</p>
        </div>
    </div>
    <div>
        <nav class="navbar navbar-expand-lg navbar-dark blue lighten-2 mb-4">
            <div class="col-sm-5 col-sm-offset-6" id="navbarSupportedContent">
                <form class="form-inline mr-auto"
                      action="${pageContext.request.contextPath}/admin/find-employee-by-surname/"
                      method="get">
                    <input class="form-control" type="text" placeholder="Search" aria-label="Search" name="surname" value="${surname}">
                    <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                            style="background-color: orange"
                            type="submit">Search
                    </button>
                </form>
            </div>
        </nav>
    </div>
    <footer class="page-footer mdb-color pt-4" style="font-size: small">
        <p class="text-center text-md-left">
            © 2020 MedHelper. Professional hospital management system.
        </p>
    </footer>
</div>
</body>
</html>
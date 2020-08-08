<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!-- shortcut icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png"
          type="image/png">
    <!-- sidebar style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/doc_style.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>

    <!-- Title -->
    <title>MedHelper</title>
</head>
<body>
<!--wrapper start-->
<div class="wrapper">
    <%@include file="shared/shared_header.jsp"%>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/reception-avt-2.jpg" alt="">
                <p>Receptionist</p>
            </center><ul>
            <li class="item" id="#patients">
                <a href="${pageContext.request.contextPath}/reception/start-page" class="menu-btn">
                    <i class="fas fa-users"></i><span>Patients</span>
                </a>
            </li>
            <li class="item" id="prescriptions">
                <a href="${pageContext.request.contextPath}/reception/add-patient" class="menu-btn">
                    <i class="fas fa-user-plus"></i><span>Add new</span>
                </a>
            </li>
            <li class="item" id="settings">
                <a href="#settings" class="menu-btn">
                    <i class="fas fa-cog"></i><span>Settings <i class="fas fa-chevron-down drop-down"></i></span>
                </a>
                <div class="sub-menu">
                    <a href="#"><i class="fas fa-lock"></i><span>Password</span></a>
                    <a href="#"><i class="fas fa-language"></i><span>Language</span></a>
                </div>
            </li>
            <li class="item">
                <a href="#" class="menu-btn">
                    <i class="fas fa-info-circle"></i><span>About</span>
                </a>
            </li>
        </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh;">
        <div class="container-fluid">
            <h3>Patients: </h3>
            <table class="table table-striped table-borderless .table-condensed ">
                <thead class="thead-mine">
                <tr class="tr-mine">
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
                            <form:form action="${pageContext.request.contextPath}/reception/patient-details/${pat.patientId}"
                                  method="get">
                                <button type="submit" class="btn btn-primary btn-sm" value="Profile"
                                        style="background-color: yellowgreen">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </form:form>
                            <form:form action="${pageContext.request.contextPath}/reception/edit-patient-data/${pat.patientId}"
                                  method="get">
                                <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                        style="background-color: yellowgreen">
                                    <i class="fas fa-edit"></i>
                                </button>
                            </form:form>
                            <form:form action="${pageContext.request.contextPath}/reception/delete-patient"
                                  method="post">
                                <input type="hidden" name="patientIdToDelete" value="${pat.patientId}">
                                <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                        style="background-color: yellowgreen">
                                    <i class="fa fa-trash"></i>
                                </button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div content="container" class="col-sm-8 col-sm-offset-4">
                <p>${message}</p>
            </div>
            <div>
                <nav class="navbar navbar-expand-lg navbar-dark blue lighten-2 mb-4">
                    <div class="col-sm-5 col-sm-offset-6" id="navbarSupportedContent">
                        <form:form class="form-inline mr-auto"
                              action="${pageContext.request.contextPath}/reception/find-patient-by-surname/"
                              method="get">
                            <input class="form-control" type="text" placeholder="Search" aria-label="Search"
                                   name="surname" value="${surname}">
                            <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                                    style="background-color: orange"
                                    type="submit">Search
                            </button>
                        </form:form>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->
</body>
</html>
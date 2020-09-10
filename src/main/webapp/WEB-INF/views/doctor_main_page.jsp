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
    <!-- popup about style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/about_popup_style.css">

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css"/>

    <!-- Title -->
    <title>MedHelper</title>
</head>
<body>
<!--wrapper start-->
<div class="wrapper">
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn">
                        <i class="fas fa-user-friends"></i><span>My patients</span>
                    </a>
                </li>
                <li class="item" id="#all-patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page/all-patients" class="menu-btn">
                        <i class="fas fa-users"></i><span>All patients</span>
                    </a>
                </li>
                <li class="item">
                    <a id="about-popup" href="#" class="menu-btn" onclick="showInfo()">
                        <i class="fas fa-info-circle"></i><span>About</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="min-height: 90vh; margin-bottom: 0; height: auto">
        <h5>PATIENTS</h5>
        <div>
            <form:form class="form-inline mr-auto"
                       action="${pageContext.request.contextPath}/doctor/find-patient-by-surname"
                       method="get">
                <input id="search-input" required class="form-control" type="text" placeholder="Enter Surname"
                       aria-label="Search"
                       name="surname"
                       oninvalid="this.setCustomValidity('The field is empty')"
                       oninput="setCustomValidity('')"
                       value="${surname}">
                <button id="search-button" class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                        style="background-color: orange"
                        type="submit"><i class="fas fa-search"></i> Search
                </button>
            </form:form>
        </div>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p id="message-not-found">${message}</p>
        </div>
        <div>
            <div class="card" style="padding: 0 5px 5px;">
                <table class="table table-striped table-borderless .table-condensed" id="datatable">
                    <thead style="background: #A4D349">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Age</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Insurance Company</th>
                        <th scope="col">Insurance Policy</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="table table-hover">
                    <c:forEach items="${doctorsPatients}" var="pat" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${pat.name}</td>
                            <td>${pat.gender.toString()}</td>
                            <td>${pat.age}</td>
                            <td>${pat.phone}</td>
                            <td>${pat.insuranceCompany}</td>
                            <td>${pat.insurancePolicyCode}</td>
                            <td class="text-right row padding-right: 5px">
                                <div style='margin-left:10px'>
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/medical-record/${pat.patientId}"
                                            method="get">
                                        <button id="medical-record-button" type="submit" class="btn btn-primary btn-sm"
                                                value="Medical Record"
                                                style="background-color: yellowgreen"
                                                title="Show patient's medical record">
                                            <i class="fas fa-file-medical-alt"></i>
                                        </button>
                                    </form:form>
                                </div>
                                <div style='margin-left:10px'>
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/show-prescription/${pat.patientId}"
                                            method="get">
                                        <button id="show-prescriptions-button" type="submit"
                                                class="btn btn-primary btn-sm" value="Prescriptions"
                                                style="background-color: yellowgreen"
                                                title="Show patient's prescriptions">
                                            <i class="fas fa-tablets"></i>
                                        </button>
                                    </form:form>
                                </div>
                                <div style='margin-left:10px'>
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/show-patient-treatment-events/${pat.patientId}"
                                            method="get">
                                        <button id="show-treatment-events-button" type="submit"
                                                class="btn btn-primary btn-sm"
                                                value="Treatment Events"
                                                style="background-color: yellowgreen"
                                                title="Show patient's treatment events">
                                            <i class="fas fa-list-ul"></i>
                                        </button>
                                    </form:form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
    <%@include file="shared/about_info_popup.jsp" %>
</div>
<!--wrapper end-->

<%@include file="shared/js_scripts.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/about_info_popup.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>

</body>
</html>

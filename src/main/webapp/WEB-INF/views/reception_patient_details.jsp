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
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/reception/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="show-med-record">
                    <c:set var="patientId" scope="session" value="${patientInfo.patientId}"></c:set>
                    <c:if test="${empty patientId}">
                        <c:set var="patientId" value="${patientId}"/>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/reception/edit-patient-data/${patientId}"
                       class="menu-btn">
                        <i class="far fa-edit"></i><span>Edit Patient</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="min-height: 90vh; height: auto; background-image: url('/resources/images/icon_med_helper.png');
background-size: contain; background-repeat: no-repeat; background-position: right">
        <div class="card" style="font-size: 18px; width: 60%; padding-left: 5%">
            <span class="details-title" style="font-weight: 700">PATIENT PROFILE:</span>
            <br>
            <div style="color: indianred;font-weight: 700"><p>${message}</p></div>
            <c:set var="patientIdToCheck" value="${patientInfo.patientId}"/>
            <c:if test="${not empty patientIdToCheck}">
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700"><i class="fas fa-user"></i>
                        Name:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic"> ${patientInfo.firstName} ${patientInfo.middleName} ${patientInfo.surname}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-venus-mars"></i> Gender:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.gender.toString()}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-birthday-cake"></i> Date of Birth:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.dateOfBirth}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-city"></i> Address:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.address}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-passport"></i> Passport Id:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.passportId}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-phone-square-alt"></i> Phone:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.phoneNumber}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-envelope"></i> Email:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.email}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-medkit"></i> Insurance Company:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.insuranceCompany}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5" style="color: darkred; font-weight: 700">
                        <i class="fas fa-medkit"></i> Insurance Policy Code:
                    </div>
                    <div class="col-md-5">
                        <p class="font-italic">${patientInfo.insurancePolicyCode}</p>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${empty patientInfo.attendingDoctorId}">
                        <p style="color: darkred; font-weight: 700"><i class="fas fa-user-nurse"></i> Attending doctor:
                            None yet</p>
                    </c:when>
                    <c:otherwise>
                        <h6 style="color: darkred; font-weight: 700">
                            <i class="fas fa-user-nurse"></i> Attending
                            doctor: ${patientInfo.attendingDoctorId.surname} ${patientInfo.attendingDoctorId.firstName}
                            <c:if test="${not empty patientInfo.attendingDoctorId.surname}">,</c:if>
                                ${patientInfo.attendingDoctorId.position}
                        </h6>
                    </c:otherwise>
                </c:choose>
                <form:form action="${pageContext.request.contextPath}/reception/appoint-doctor/" method="get">
                    <input type="hidden" name="patientId" value="${patientInfo.patientId}">
                    <button type="submit" class="btn btn-primary">Appoint a doctor</button>
                </form:form>
            </c:if>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>






<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<c:set var="docId" scope="page"><sec:authentication
        property="principal.employee.employeeId"/></c:set>
<div class="wrapper">
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="#show-med-record">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${medicalRecord.medicalRecordId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
                <c:if test="${docId eq medicalRecord.patient.attendingDoctorId.employeeId}">
                    <li class="item" id="#hospitalisation">
                        <a href="${pageContext.request.contextPath}/doctor/medical-record/hospitalisation/${medicalRecord.medicalRecordId}"
                           class="menu-btn"><i class="fas fa-procedures"></i><span>Hospitalisation</span>
                        </a>
                    </li>
                    <li class="item" id="#add-diagnosis">
                        <a href="${pageContext.request.contextPath}/doctor/medical-record/add-diagnosis/${medicalRecord.medicalRecordId}"
                           class="menu-btn">
                            <i class="fas fa-stethoscope"></i><span>Add diagnosis</span>
                        </a>
                    </li>
                </c:if>
                <li class="item" id="#show-prescriptions">
                    <a href="${pageContext.request.contextPath}/doctor/show-prescription/${medicalRecord.medicalRecordId}"
                       class="menu-btn"><i class="fas fa-prescription"></i>Prescriptions</span>
                    </a>
                </li>
                <c:if test="${docId eq medicalRecord.patient.attendingDoctorId.employeeId}">
                    <li class="item" id="#add-prescription">
                        <a href="${pageContext.request.contextPath}/doctor/add-prescription/${medicalRecord.medicalRecordId}"
                           class="menu-btn">
                            <i class="fas fa-tablets"></i><span>Add prescription</span>
                        </a>
                    </li>
                </c:if>
                <li class="item" id="#events">
                    <a href="${pageContext.request.contextPath}/doctor/show-patient-treatment-events/${medicalRecord.medicalRecordId}"
                       class="menu-btn">
                        <i class="fas fa-list-ul"></i><span>Treatment Events</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; min-height: 90vh; height: auto">
        MEDICAL RECORD
        <div class="card">
            <p style="font-weight: bold">Personal info:</p>
            <div style=" font-size: 16px">
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Name:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.surname} ${medicalRecord.patient.firstName} ${medicalRecord.patient.middleName}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Gender:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.gender.toString()}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Date of Birth:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.dateOfBirth}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Address:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.address}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Phone:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.phoneNumber}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Email:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.email}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Insurance Company:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.insuranceCompany}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Insurance Policy Code:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.patient.insurancePolicyCode}
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <p style="font-weight: bold">Hospitalization:</p>
            <div style="font-size: 16px">
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Status:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.hospitalStayStatus.toString()}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Department:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.hospitalDepartment}
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="color: darkred; font-weight: 400">
                        Ward:
                    </div>
                    <div class="col-md-5">
                        ${medicalRecord.hospitalWard}
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${empty medicalRecord.clinicalDiagnosis}">
                <div class="card">
                    <p style="font-weight: bold">Clinical diagnosis: </p>
                    The patient has not yet been diagnosed.
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${medicalRecord.clinicalDiagnosis}" var="diagnosis">
                    <div class="card">
                        <p style="font-weight: bold">Clinical Diagnosis:</p>
                        <div style="font-size: 16px">
                            <div class="row">
                                <div class="col-md-3" style="color: darkred; font-weight: 400">
                                    Main Disease:
                                </div>
                                <div class="col-md-5" id="main-disease">
                                        ${diagnosis.mainDisease}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3" style="color: darkred; font-weight: 400">
                                    ICD-10:
                                </div>
                                <div class="col-md-5">
                                        ${diagnosis.icd10Code}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3" style="color: darkred; font-weight: 400">
                                    Accompanying Pathology:
                                </div>
                                <div class="col-md-5">
                                        ${diagnosis.accompanyingPathology}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3" style="color: darkred; font-weight: 400">
                                    Diagnosis Description:
                                </div>
                                <div class="col-md-5">
                                        ${diagnosis.fullDiagnosisDescription}
                                </div>
                            </div>
                        </div>

                        <c:if test="${docId eq medicalRecord.patient.attendingDoctorId.employeeId}">
                            <div class="row" style="padding-top: 15px; padding-left: 30px">
                                <div class="col-sm-offset-1">
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/edit-clinical-diagnosis/${diagnosis.clinicalDiagnosisId}"
                                            method="get">
                                        <button id="edit-diagnosis-button" type="submit" class="btn btn-primary btn-sm"
                                                style="background-color: yellowgreen">
                                            <i class="fas fa-edit"> Edit</i>
                                        </button>
                                    </form:form>
                                </div>
                                <div class="col-sm-2">
                                    <form:form action="${pageContext.request.contextPath}/doctor/delete-diagnosis"
                                               method="post">
                                        <input type="hidden" id="cDiagnosisIdToDelete" name="cDiagnosisIdToDelete"
                                               value="${diagnosis.clinicalDiagnosisId}">
                                        <input type="hidden" id="medRecId" name="medRecId"
                                               value="${medicalRecord.medicalRecordId}">
                                        <button type="submit" class="btn btn-primary btn-sm"
                                                style="background-color: darkorange; color: black">
                                            <i class="fas fa-trash"> Delete</i>
                                        </button>
                                    </form:form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
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
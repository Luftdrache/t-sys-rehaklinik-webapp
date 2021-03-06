<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.TreatmentType" %>
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
    <!-- form style-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form_style.css">
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
<c:set var="docId" scope="page"><sec:authentication
        property="principal.employee.employeeId"/></c:set>
<!--wrapper start-->
<div class="wrapper">
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn"
                       style="font-size: 20px;">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="show-med-record" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${CDToEdit.medicalRecord.medicalRecordId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; height: 90vh">
        <div class="container-fluid">
            <div content="container" class="col-sm-8 col-sm-offset-2"
                 style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
                <c:if test="${docId ne attendingDoctorId}">
                    <h4>Sorry, you are not allowed to make changes for this patient</h4>
                </c:if>
                <c:if test="${docId eq attendingDoctorId}">
                    <form:form action="${pageContext.request.contextPath}/doctor/edit-clinical-diagnosis" method="post"
                               class="form-horizontal"
                               role="form">
                        <div style="padding-left: 20%">
                            <h2>Edit Clinical Diagnosis</h2>
                            <span class="help-block">*Required fields</span>
                        </div>
                        <div style="color: indianred;font-weight: 700; font-size: 20px"><p>${message}</p></div>
                        <c:set var="cDiagnosisIdToEdit" value="${message}"/>
                        <c:if test="${empty cDiagnosisIdToEdit}">
                            <!-- HIDDEN -->
                            <input type="hidden" id="clinicalDiagnosisId" name="clinicalDiagnosisId"
                                   value="${CDToEdit.clinicalDiagnosisId}"/>
                            <input type="hidden" id="medicalRecord.medicalRecordId" name="medicalRecord.medicalRecordId"
                                   value="${CDToEdit.medicalRecord.medicalRecordId}"/>
                            <div class="form-group">
                                <label for="mainDisease" class="col-sm-4 control-label">Main Disease*</label>
                                <div class="col-sm-6">
                                    <input type="text" id="mainDisease" value="${CDToEdit.mainDisease}"
                                           name="mainDisease" placeholder="Main Disease" class="form-control"
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="icd10Code" class="col-sm-4 control-label">ICD-10 Code*</label>
                                <div class="col-sm-6">
                                    <input type="text" id="icd10Code" value="${CDToEdit.icd10Code}"
                                           name="icd10Code" placeholder="ICD-10 Code" class="form-control"
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="accompanyingPathology" class="col-sm-4 control-label">Accompanying
                                    Pathology</label>
                                <div class="col-sm-6">
                                    <input type="text" id="accompanyingPathology"
                                           value="${CDToEdit.accompanyingPathology}"
                                           name="accompanyingPathology" placeholder="Accompanying Pathology"
                                           class="form-control"
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fullDiagnosisDescription" class="col-sm-4 control-label">
                                    Full Diagnosis Description
                                </label>
                                <div class="col-sm-6">
                            <textarea rows="8" id="fullDiagnosisDescription"
                                      name="fullDiagnosisDescription"
                                      placeholder="Full Diagnosis Description"
                                      class="form-control">${CDToEdit.fullDiagnosisDescription}</textarea>
                                </div>
                            </div>
                            <div style="padding-left: 50%">
                                <input id="submit-edit-diagnosis" type="submit" class="btn login_btn" value="Edit"
                                       style="background-color: orange; opacity: 0.9;"/>
                            </div>
                        </c:if>
                    </form:form>
                </c:if>
            </div>
        </div>
        <!-- *******MAIN CONTAINER******* -->
    </div>
    <!--wrapper end-->
</div>

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


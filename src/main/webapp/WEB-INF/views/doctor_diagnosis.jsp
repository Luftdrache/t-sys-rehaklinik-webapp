<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tsystems.rehaklinik.types.HospitalStayStatus" %>
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
<!--wrapper start-->
<div class="wrapper">
    <!--header menu start-->
    <div class="header">
        <div class="header-menu">
            <div class="title">Med<span>Helper</span></div>
            <div class="sidebar-btn">
                <i class="fas fa-bars"></i>
            </div>
            <ul>
                <li><input type="button" value="Sign out" class="btn login_btn"
                           style="background-color: orange; padding: 5px; margin-top: 15px"></li>
            </ul>
        </div>
    </div>
    <!--header menu end-->
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.png" alt="">
                <p style="font-size: 20px; ">Doctor</p>
            </center>
            <li class="item" id="#patients">
                <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn"
                   style="font-size: 20px;">
                    <i class="fas fa-clinic-medical"></i><span>Main page</span>
                </a>
            </li>
            <li class="item" id="show-med-record" style="font-size: 20px;">
                <a href="${pageContext.request.contextPath}/doctor/medical-record/${medrec}"
                   class="menu-btn">
                    <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                </a>
            </li>
            <li class="item" id="edit-med-record" style="font-size: 20px;">
                <a href="#" class="menu-btn">
                    <i class="far fa-edit"></i><span>Edit</span>
                </a>
            </li>
            <li class="item" id="add-prescription" style="font-size: 20px;">
                <a href="#" class="menu-btn">
                    <i class="fas fa-tablets"></i><span>Add prescription</span>
                </a>
            </li>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; height: auto">
        <div class="container-fluid">
            <div content="container" class="col-sm-8 col-sm-offset-2"
                 style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
                <form action="${pageContext.request.contextPath}/doctor/medical-record/diagnosis/" method="post"
                      class="form-horizontal"
                      role="form">
                    <div style="padding-left: 20%">
                        <h2>Specify Diagnosis
                            for ${diagnosisToEdit.patient.firstName} ${diagnosisToEdit.patient.surname}</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <!-- hidden-->
                    <c:choose>
                        <c:when test="${empty diagnosisToEdit.clinicalDiagnosis.clinicalDiagnosisId}">
                            <c:set var="diaId" value="0"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="diaId" value="${diagnosisToEdit.clinicalDiagnosis.clinicalDiagnosisId}"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" id="MedRecId" name="medicalRecordId"
                           value="${diagnosisToEdit.medicalRecordId}"/>
                    <input type="hidden" id="hospitalStayStatus" name="hospitalStayStatus"
                           value="${diagnosisToEdit.hospitalStayStatus}">
                    <input type="hidden" id="diagnosisId" name="clinicalDiagnosis.clinicalDiagnosisId"
                           value=${diaId}>
                    <!-- hidden-->
                    <div class="form-group">
                        <label for="clinicalDiagnosis.mainDisease" class="col-sm-4 control-label">
                            Main Disease*
                        </label>
                        <div class="col-sm-6">
                            <input type="text" id="clinicalDiagnosis.mainDisease" name="clinicalDiagnosis.mainDisease"
                                   value="${diagnosisToEdit.clinicalDiagnosis.mainDisease}" placeholder="Main Disease"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="clinicalDiagnosis.icd10Code" class="col-sm-4 control-label">ICD-10*</label>
                        <div class="col-sm-6">
                            <input type="text" id="clinicalDiagnosis.icd10Code" name="clinicalDiagnosis.icd10Code"
                                   value="${diagnosisToEdit.clinicalDiagnosis.icd10Code}"
                                   placeholder="ICD-10" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="clinicalDiagnosis.accompanyingPathology" class="col-sm-4 control-label">
                            Accompanying Pathology
                        </label>
                        <div class="col-sm-6">
                            <input type="text" id="clinicalDiagnosis.accompanyingPathology"
                                   name="clinicalDiagnosis.accompanyingPathology"
                                   value="${diagnosisToEdit.clinicalDiagnosis.accompanyingPathology}"
                                   placeholder="Accompanying Pathology" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="clinicalDiagnosis.fullDiagnosisDescription" class="col-sm-4 control-label">
                            Full Diagnosis Description
                        </label>
                        <div class="col-sm-6">
                            <textarea rows="8" id="clinicalDiagnosis.fullDiagnosisDescription"
                                      name="clinicalDiagnosis.fullDiagnosisDescription"
                                      value="${diagnosisToEdit.clinicalDiagnosis.fullDiagnosisDescription}"
                                      placeholder="Full Diagnosis Description" class="form-control">
                            </textarea>
                        </div>
                    </div>
                    <div style="padding-left: 50%">
                        <input type="submit" class="btn login_btn" value="Set Diagnosis"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- *******MAIN CONTAINER******* -->
    </div>
    <!--wrapper end-->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".sidebar-btn").click(function () {
            $(".wrapper").toggleClass("collapse");
        });
    });
</script>

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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.png" alt="">
                <p style="font-size: 20px;">Doctor</p>
            </center>
            <ul>
                <li class="item" id="#patients" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="#show-med-record" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${medrec}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
                <li class="item" id="#hospitalisation" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/hospitalisation/${medrec}"
                       class="menu-btn"><i class="fas fa-procedures"></i><span>Hospitalisation</span>
                    </a>
                </li>
                <li class="item" id="#specify-diagnosis" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/add-diagnosis/${medrec}"
                       class="menu-btn">
                        <i class="fas fa-stethoscope"></i><span>Specify diagnosis</span>
                    </a>
                </li>
                <li class="item" id="#add-prescription" style="font-size: 20px;">
                    <a href="#" class="menu-btn">
                        <i class="fas fa-tablets"></i><span>Add prescription</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh;">
        <form:form action="${pageContext.request.contextPath}/doctor/medical-record/hospitalisation/" method="post"
              class="form-horizontal"
              role="form">
            <div style="padding-left: 20%">
                <h2>Hospitalisation settings
                    for patient ${hospitalisationToEdit.patient.firstName} ${hospitalisationToEdit.patient.surname}</h2>
                <span class="help-block">*Required fields</span>
            </div>
            <!-- HIDDEN-->
            <input type="hidden" id="MedRecId" name="medicalRecordId"
                   value="${hospitalisationToEdit.medicalRecordId}"/>
            <input type="hidden" id="patientId" name="patient.patientId"
                   value="${hospitalisationToEdit.patient.patientId}">
            <!-- HIDDEN-->
            <div class="form-group">
                <label for="hospitalStayStatus" class="col-sm-4 control-label">Stay Status*</label>
                <div class="col-sm-6">
                    <select id="hospitalStayStatus" name="hospitalStayStatus"
                            value="${hospitalisationToEdit.hospitalStayStatus}"
                            class="form-control">
                        <c:forEach items="${HospitalStayStatus.values()}" var="hStayStatus">
                            <option>${hStayStatus.toString()}</option>
                        </c:forEach>
                        <%--                        <option selected>${hospitalisationToEdit.hospitalStayStatus}</option>--%>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="hospitalDepartment" class="col-sm-4 control-label">Hospital Department</label>
                <div class="col-sm-6">
                    <input type="text" id="hospitalDepartment" value="${hospitalisationToEdit.hospitalDepartment}"
                           name="hospitalDepartment" placeholder="Hospital Department" class="form-control"
                           autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="hospitalWard" class="col-sm-4 control-label">Ward</label>
                <div class="col-sm-6">
                    <input type="number" min="0" id="hospitalWard" name="hospitalWard"
                           value="${hospitalisationToEdit.hospitalWard}"
                           placeholder="Ward" class="form-control" autofocus>
                </div>
            </div>
            <div style="padding-left: 50%">
                <input type="submit" class="btn login_btn" value="Set Changes"
                       style="background-color: orange; opacity: 0.9;"/>
            </div>
        </form:form>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->

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
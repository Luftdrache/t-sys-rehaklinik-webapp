<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <p>Doctor</p>
            </center>
            <li class="item" id="#patients">
                <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn">
                    <i class="fas fa-clinic-medical"></i><span>Main page</span>
                </a>
            </li>
            <li class="item" id="show-med-record">
                <a href="#" class="menu-btn">
                    <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                </a>
            </li>
            <li class="item" id="edit-med-record">
                <a href="#" class="menu-btn">
                    <i class="far fa-edit"></i><span>Edit</span>
                </a>
            </li>
            <li class="item" id="add-prescription">
                <a href="#" class="menu-btn">
                    <i class="fas fa-tablets"></i><span>Add prescription</span>
                </a>
            </li>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container">
        MEDICAL RECORD
        <div class="card">
            <p>Personal info:</p>
            <p>
                Name: ${medicalRecord.patient.surname} ${medicalRecord.patient.firstName} ${medicalRecord.patient.middleName}</p>
            <p>Gender: ${medicalRecord.patient.gender}</p>
            <p>Date of Birth: ${medicalRecord.patient.dateOfBirth}</p>
            <p>Address: ${medicalRecord.patient.address}</p>
            <p>Phone: ${medicalRecord.patient.phoneNumber}</p>
            <p>Email: ${medicalRecord.patient.email}</p>
            <p>Insurance Compane: ${medicalRecord.patient.insuranceCompany}</p>
            <p>Insurance Policy Code: ${medicalRecord.patient.insurancePolicyCode}</p>
        </div>
        <div class="card">
            <p>Hospitalization:</p>
            <p>Status: ${medicalRecord.hospitalStayStatus}</p>
            <p>Department: ${medicalRecord.hospitalDepartment}</p>
            <p>Ward: ${medicalRecord.hospitalWard}</p>
        </div>
        <div class="card">
            <p>Clinical Diagnosis:</p>
            <p>Main Disease: ${medicalRecord.clinicalDiagnosis.mainDisease}</p>
            <p>ICD-10: ${medicalRecord.clinicalDiagnosis.icd10Code}</p>
            <p>Accompanying Pathology: ${medicalRecord.clinicalDiagnosis.accompanyingPathology}</p>
            <p>Diagnosis Description: ${medicalRecord.clinicalDiagnosis.fullDiagnosisDescription}</p>
        </div>
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
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
<!--wrapper start-->
<div class="wrapper">
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="patients">
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
                <li class="item" id="#show-prescriptions" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/show-prescription/${medrec}"
                       class="menu-btn"><i class="fas fa-prescription"></i>Prescriptions</span>
                    </a>
                </li>
                <li class="item" id="add-prescription" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/add-prescription/${medrec}"
                       class="menu-btn">
                        <i class="fas fa-tablets"></i><span>Add prescription</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="min-height:90vh; height: auto;
    background-image: url('/resources/images/icon_med_helper.png'); background-size: contain;
    background-repeat: no-repeat; background-position: right">
        <div class="card" style="font-size: 18px; width: 60%; padding-left: 8%; border-radius: 5%">
            <br>
            <span class="details-title" style="font-weight: 700">
                 PRESCRIPTION DETAILS
            </span>
            <br><br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700"><i class="fas fa-file-medical"></i>
                    Name:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${prescription.medicineProcedureName}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700"><i class="fas fa-medkit"></i>
                    Treatment Type:
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.treatmentType}</div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700"><i class="fas fa-pills"></i>
                    Dose:
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.dose}</div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="fas fa-syringe"></i> Administering Medication Method
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.administeringMedicationMethod}</div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="far fa-clock"></i> Start Treatment Period:
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.startTreatment}</div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="fas fa-clock"></i> End Treatment Period:
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.endTreatment}</div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="fas fa-user-clock"></i> Time:
                </div>
                <div class="col-md-5">
                    <div class="font-italic">${prescription.precisionTime}</div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="far fa-calendar-alt"></i> Days:
                </div>
                <div class="col-md-6">
                    ${prescription.sunday}
                    ${prescription.monday}
                    ${prescription.tuesday}
                    ${prescription.wednesday}
                    ${prescription.thursday}
                    ${prescription.friday}
                    ${prescription.saturday}
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5" style="color: darkred; font-weight: 700">
                    <i class="fas fa-utensils"></i> Additional:
                </div>
                <div class="col-md-5">
                    ${prescription.beforeMeals}
                    ${prescription.atMeals}
                    ${prescription.afterMeals}
                </div>
            </div>
            <div style="color: darkred;font-weight: 700">${message}</div>
        </div>
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



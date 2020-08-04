<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/nurse-avt.png" alt="">
                <p style="font-size: 20px; ">Nurse</p>
            </center>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/nurse/start-page" class="menu-btn"
                       style="font-size: 20px;">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style=" background-image: url('/resources/images/icon_med_helper.png');
    background-size: contain; background-repeat: no-repeat; background-position: right; background-attachment: scroll">
        <div class="card" style="font-size: 18px; width: 65%; padding-left: 5%; border-radius: 5%">
            <br>
            <div class="row">
                <div class="col-sm-offset-1">
                <span class="details-title" style="font-weight: 700">
                TREATMENT EVENT DETAILS </span>
                </div>
                <c:set var="status" value="${treatmentEventDetails.treatmentEventStatus}"></c:set>
                <c:if test="${status != 'CANCELLED'}">
                <div class="col-sm-offset-5">
                    <form action="${pageContext.request.contextPath}/nurse/ca/${empl.employeeId}"
                          method="get">
                        <button type="submit" class="btn btn-primary btn-sm"
                                style="background-color: darkred; font-size: 16px">
                            <i class="fas fa-times"> Cancel</i>
                        </button>
                    </form>
                </div>
                </c:if>
            </div>
            <br><br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-user"></i>&emsp;Patient:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.patient}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-hospital"></i>&emsp;Department:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.hospitalDepartment}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-bed"></i>&emsp;Ward:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.hospitalWard}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-medkit"></i>&emsp;Treatment:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.medicineProcedureName}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-procedures"></i> / <i class="fas fa-tablets"></i>&emsp;Type:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.treatmentType.toString()}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-pills"></i>&emsp;Dose:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.dose}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-syringe"></i>&emsp;Method:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.administeringMedicationMethod}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="far fa-calendar-alt"></i>&emsp;Date:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.treatmentEventDate}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="far fa-clock"></i>&emsp;Time:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.treatmentEventTime}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-info"></i>&emsp;Status:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.treatmentEventStatus}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4" style="color: darkred; font-weight: 700">
                    <i class="fas fa-user-clock"></i>&emsp;Treatment Period:
                </div>
                <div class="col-md-6">
                    <p class="font-italic">${treatmentEventDetails.treatmentPeriodStartDate}
                        &mdash;
                        ${treatmentEventDetails.treatmentPeriodEndDate}
                    </p>
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




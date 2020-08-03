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
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.png" alt="">
                <p>Doctor</p>
            </center>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="#show-med-record">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${patientId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
                <li class="item" id="#hospitalisation">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/hospitalisation/${patientId}"
                       class="menu-btn"><i class="fas fa-procedures"></i><span>Hospitalisation</span>
                    </a>
                </li>
                <li class="item" id="#specify-diagnosis">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/add-diagnosis/${patientId}"
                       class="menu-btn">
                        <i class="fas fa-stethoscope"></i><span>Specify diagnosis</span>
                    </a>
                </li>
                <li class="item" id="#add-prescription">
                    <a href="${pageContext.request.contextPath}/doctor/add-prescription/${patientId}" class="menu-btn">
                        <i class="fas fa-tablets"></i><span>Add prescription</span>
                    </a>
                </li>
                <li class="item" id="#edit-med-record">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/edit/${patientId}"
                       class="menu-btn">
                        <i class="far fa-edit"></i><span>Edit</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh;">
        Patient's Prescriptions
        <table class="table table-striped table-borderless .table-condensed ">
            <thead class="thead-mine">
            <tr class="tr-mine">
                <th scope="col"> </th>
                <th scope="col">Name</th>
                <th scope="col">Type</th>
                <th scope="col">Dose</th>
                <th scope="col">Method</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody class="table table-hover">
            <c:forEach items="${patientPrescriptionsList}" var="patPrescription">
                <tr>
                    <td>#</td>
                    <td>${patPrescription.name}</td>
                    <td>${patPrescription.treatmentType}</td>
                    <td>${patPrescription.dose}</td>
                    <td>${patPrescription.administeringMedicationMethod}</td>
                    <td>${patPrescription.startTreatment}</td>
                    <td>${patPrescription.endTreatment}</td>
                    <td class="text-right row padding-right: 5px">
                        <form action="${pageContext.request.contextPath}/admin/employee-details/${empl.employeeId}"
                              method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Profile"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-eye"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/doctor/edit-prescription/${patPrescription.prescriptionId}"
                              method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-edit"></i>
                            </button>
                        </form>
                        <form action="" method="post">
                            <input type="hidden" name="patient" value="${patientId}">
                            <input type="hidden" name="prescriptionIdToCancel" value="${patPrescription.prescriptionId}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Cancel"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-ban"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/doctor/delete-prescription" method="post">
                            <input type="hidden" name="patient" value="${patientId}">
                            <input type="hidden" name="prescriptionIdToDelete" value="${patPrescription.prescriptionId}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                    style="background-color: yellowgreen">
                                <i class="fa fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${patientPrescriptionsMessage}</p>
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
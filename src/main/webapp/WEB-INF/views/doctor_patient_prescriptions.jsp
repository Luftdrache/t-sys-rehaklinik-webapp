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
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="#add-prescription">
                    <a href="${pageContext.request.contextPath}/doctor/add-prescription/${patientId}" class="menu-btn">
                        <i class="fas fa-tablets"></i><span>Add prescription</span>
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
                <li class="item" id="#events">
                    <a href="${pageContext.request.contextPath}/doctor/show-patient-treatment-events/${patientId}"
                       class="menu-btn">
                        <i class="fas fa-list-ul"></i><span>Treatment Events</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="min-height: 90vh; margin-bottom: 0; height: auto">
        Patient's Prescriptions

        <div class="card" style="padding: 0 5px 5px;">
                <table class="table table-striped table-borderless .table-condensed" id="datatable">
                    <thead style="background: #A4D349;">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Type</th>
                        <th scope="col">Start Date</th>
                        <th scope="col">End Date</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="table table-hover">
                    <c:forEach items="${patientPrescriptionsList}" var="patPrescription" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${patPrescription.name}</td>
                            <td>${patPrescription.treatmentType}</td>
                            <td>${patPrescription.startTreatment}</td>
                            <td>${patPrescription.endTreatment}</td>
                            <td>${patPrescription.prescriptionStatus}</td>
                            <td class="text-right row padding-right: 5px">
                                <div style='margin-left:10px'>
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/prescription-details/${patPrescription.prescriptionId}"
                                            method="get">
                                        <input type="hidden" name="medRecordId" , id="medRecordId" value="${patientId}">
                                        <button type="submit" class="btn btn-primary btn-sm" value="Details"
                                                style="background-color: yellowgreen;"
                                                title="Show details">
                                            <i class="fas fa-eye"></i>
                                        </button>
                                    </form:form>
                                </div>
                                <div style='margin-left:10px'>
                                    <c:if test="${patPrescription.prescriptionStatus!= 'CANCELLED'}">
                                    <form:form
                                            action="${pageContext.request.contextPath}/doctor/edit-prescription/${patPrescription.prescriptionId}"
                                            method="get">
                                        <input type="hidden" name="patientId" , id="patientId" value="${patientId}">
                                        <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                                style="background-color: yellowgreen"
                                                title="Edit prescription">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                    </form:form>
                                </div>
                                <div style='margin-left:10px'>
                                    <form:form action="${pageContext.request.contextPath}/doctor/cancel-prescription"
                                               method="post">
                                        <input type="hidden" name="patientId" , id="patientId" value="${patientId}">
                                        <input type="hidden" name="prescriptionIdToCancel"
                                               value="${patPrescription.prescriptionId}">
                                        <button type="submit" class="btn btn-primary btn-sm" value="Cancel"
                                                style="background-color: yellowgreen"
                                                title="Cancel prescription">
                                            <i class="fas fa-ban"></i>
                                        </button>
                                    </form:form>
                                    </c:if>
                                </div>
                                <div style='margin-left:10px'>
                                    <form:form action="${pageContext.request.contextPath}/doctor/delete-prescription"
                                               method="post">
                                        <input type="hidden" name="patient" value="${patientId}">
                                        <input type="hidden" name="prescriptionIdToDelete"
                                               value="${patPrescription.prescriptionId}">
                                        <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                                style="background-color: yellowgreen"
                                                title="Delete prescription">
                                            <i class="fa fa-trash"></i>
                                        </button>
                                    </form:form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div>
                </div>
                <div content="container" class="col-sm-8 col-sm-offset-4">
                    <p>${patientPrescriptionsMessage}</p>
                </div>
            <!-- *******MAIN CONTAINER******* -->
        </div>
        <!--wrapper end-->
        <%@include file="shared/js_scripts.jsp" %>
        <script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</body>
</html>
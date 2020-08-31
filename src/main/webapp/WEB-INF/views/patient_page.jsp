<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- shortcut icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png"
          type="image/png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!-- sidebar style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/doc_style.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <!-- popup cansel style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cancel_t_event_popup.css">
    <!-- popup about style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/about_popup_style.css">

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
                <li class="item">
                    <a href="${pageContext.request.contextPath}/patient/send-email" class="menu-btn">
                        <i class="fas fa-envelope"></i><span>Send to Email</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <%--    <c:set var="treatmentEventId" value="7" scope="application"/>--%>
    <div class="main-container" style="min-height: 90vh; height: auto;">
        <c:forEach items="${medicalRecord.clinicalDiagnosis}" var="diagnosis">
            <div class="card">
                <p style="font-weight: bold">Clinical Diagnosis:</p>
                <div style="font-size: 16px">
                    <div class="row">
                        <div class="col-md-3" style="color: darkred; font-weight: 400">
                            Main Disease:
                        </div>
                        <div class="col-md-5">
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
            </div>
        </c:forEach>

        <div class="card" style="padding: 0 5px 5px; float: bottom">
            <table class="table table-striped table-borderless .table-condensed" id="datatable">
                <thead style="background: #A4D349">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Time</th>
                    <th scope="col">Date</th>
                    <th scope="col">Status</th>
                    <th scope="col">Cancel Reason</th>
                    <th scope="col">Treatment</th>
                    <th scope="col">Type</th>
                    <th scope="col">Dose</th>
                    <th scope="col">Method</th>
                    <th scope="col">Start Period</th>
                    <th scope="col">End Period</th>
                </tr>
                </thead>
                <tbody class="table table-hover" style="text-align: center">
                <c:forEach items="${treatmentEventList}" var="tEvent" varStatus="status">
                    <tr style=" padding: 0">
                        <td>${status.count}</td>
                        <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventTime}</td>
                        <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventDate}</td>
                        <td>${tEvent.treatmentEventStatus}</td>
                        <td>${tEvent.cancelReason}</td>
                        <td>${tEvent.medicineProcedureName}</td>
                        <td>${tEvent.treatmentType}</td>
                        <td>${tEvent.dose}</td>
                        <td>${tEvent.administeringMedicationMethod}</td>
                        <td>${tEvent.treatmentPeriodStartDate}</td>
                        <td>${tEvent.treatmentPeriodEndDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${message}</p>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<%@include file="shared/js_scripts.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</body>
</html>
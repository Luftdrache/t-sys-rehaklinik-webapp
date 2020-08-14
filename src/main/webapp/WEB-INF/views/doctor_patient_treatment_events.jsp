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
    <!-- popup style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cancel_t_event_popup.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">


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
                <li class="item" id="#show-med-record">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${currentPatientId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
                <li class="item" id="#show-prescriptions">
                    <a href="${pageContext.request.contextPath}/doctor/show-prescription/${currentPatientId}"
                       class="menu-btn"><i class="fas fa-prescription"></i>Prescriptions</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <%--    <c:set var="treatmentEventId" value="7" scope="application"/>--%>
    <div class="main-container" style="height: 90vh;">
        <h5>${tableHeader}</h5>
        <div style="float:left; margin-bottom: 10px">
            <form:form class="form-inline mr-auto"
                       action="${pageContext.request.contextPath}/doctor/find-treatment-event-by-name/${currentPatientId}"
                       method="get">
                <input class="form-control" type="text" placeholder="Enter Treatment Name" aria-label="Search"
                       name="tEventName"
                       value="${tEventName}">
                <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                        style="background-color: orange"
                        type="submit"><i class="fas fa-search"></i> Search
                </button>
            </form:form>
        </div>
        <table class="table table-striped table-borderless .table-condensed " style="text-align: center">
            <thead class="thead-mine">
            <tr class="tr-mine">
                <th scope="col">Id</th>
                <th scope="col">Time</th>
                <th scope="col">Data</th>
                <th scope="col">Status</th>
                <th scope="col">Patient</th>
                <th scope="col">Treatment</th>
                <th scope="col">Type</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody class="table table-hover" style="text-align: center">
            <c:forEach items="${treatmentEventList}" var="tEvent" varStatus="status">
                <tr style=" padding: 0">
                    <td>${status.count}</td>
                    <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventTime}</td>
                    <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventDate}</td>
                    <td>${tEvent.treatmentEventStatus}</td>
                    <td>${tEvent.patient}</td>
                    <td>${tEvent.medicineProcedureName}</td>
                    <td>${tEvent.treatmentType.toString()}</td>
                    <td class="text-right row">
                        <div style='margin-left:20px'>
                            <form:form
                                    action="${pageContext.request.contextPath}/doctor/treatment-event-details/${tEvent.treatmentEventId}"
                                    method="get">
                                <button type="submit" class="btn btn-primary btn-sm" value="Details"
                                        title="See details"
                                        style="background-color: yellowgreen">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </form:form>
                        </div>
                        <c:if test="${tEvent.treatmentEventStatus != 'CANCELLED' && tEvent.treatmentEventStatus != 'COMPLETED'}">
                            <div style='margin-left:10px'>
                                <form:form
                                        action="${pageContext.request.contextPath}/doctor/treatment-event-set-completed"
                                        method="post">
                                    <input type="hidden" id="treatmentEventId" name="treatmentEventId"
                                           value="${tEvent.treatmentEventId}">
                                    <input type="hidden" id="patientId" name="patientId"
                                           value="${tEvent.patientId}">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Completed"
                                            title='Change status to "Completed"'
                                            style="background-color: yellowgreen">
                                        <i class="fas fa-check"></i>
                                    </button>
                                </form:form>
                            </div>
                            <div style='margin-left:10px'>
                                <form:form action="${pageContext.request.contextPath}/doctor/cancel-treatment-event"
                                           method="post"
                                           class="form-horizontal"
                                           role="form">
                                    <input type="hidden" id="tEvent" name="tEvent" value="${tEvent.treatmentEventId}"/>
                                    <input type="hidden" id="patientId" name="patientId" value="${tEvent.patientId}"/>
                                    <button type="submit" id="cancel-button" name="cancel-button"
                                            class="btn btn-primary btn-sm"
                                            value="Cancel" style="background-color: yellowgreen"
                                            title="Cancel treatment event">
                                        <i class="fas fa-times"></i>
                                    </button>
                                </form:form>
                            </div>
                        </c:if>
                        <div style='margin-left:10px'>
                            <form:form action="${pageContext.request.contextPath}/doctor/delete-treatment-event"
                                       method="post">
                                <input type="hidden" id="tEventIdToDelete" name="tEventIdToDelete"
                                       value="${tEvent.treatmentEventId}">
                                <input type="hidden" id="patient" name="patient"
                                       value="${tEvent.patientId}">
                                <button type="submit" class="btn btn-primary btn-sm"
                                        title="Delete treatment event"
                                        style="background-color: yellowgreen; color: black">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form:form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${message}</p>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>

<script async src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js"
        integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J"
        crossorigin="anonymous"></script>
</body>
</html>

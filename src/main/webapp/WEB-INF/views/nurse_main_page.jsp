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
                <li class="item" id="events">
                    <a href="${pageContext.request.contextPath}/nurse/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="urgentEvents">
                    <a href="${pageContext.request.contextPath}/nurse/urgent-treatment-events" class="menu-btn">
                        <i class="fas fa-ambulance"></i><span>Urgent</span>
                    </a>
                </li>
                <li class="item" id="todayEvents">
                    <a href="${pageContext.request.contextPath}/nurse/today-treatment-events" class="menu-btn">
                        <i class="far fa-calendar-plus"></i><span>Today</span>
                    </a>
                </li>
                <li class="item" id="completedEvents">
                    <a href="${pageContext.request.contextPath}/nurse/show-completed-treatment-events" class="menu-btn">
                        <i class="fas fa-list-ol"></i><span>Completed</span>
                    </a>
                </li>
                <li class="item" id="overdueEvents">
                    <a href="${pageContext.request.contextPath}/nurse/show-overdue-treatment-events" class="menu-btn">
                        <i class="fas fa-hourglass-end"></i><span>Overdue</span>
                    </a>
                </li>
                <li class="item">
                    <a href="#" class="menu-btn" onclick="showInfo()">
                        <i class="fas fa-info-circle"></i><span>About</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <%--    <c:set var="treatmentEventId" value="7" scope="application"/>--%>
    <div class="main-container" style="min-height: 90vh; height: auto;">
        <%
            response.setIntHeader("Refresh", 300);
        %>
        <h5>${tableHeader}</h5>
        <div>
            <div style="float:left; margin-bottom: 10px">
                <form:form class="form-inline mr-auto"
                           action="${pageContext.request.contextPath}/nurse/find-events-by-surname"
                           method="get">
                    <input required class="form-control" type="text" placeholder="Enter Surname" aria-label="Search"
                           name="patientSurname"
                           oninvalid="this.setCustomValidity('The field is empty')"
                           oninput="setCustomValidity('')"
                           value="${patientSurname}">
                    <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                            style="background-color: orange"
                            type="submit"><i class="fas fa-search"></i> Search
                    </button>
                </form:form>
            </div>
            <div style="float: right;margin-right: 30px">
                <jsp:useBean id="now" class="java.util.Date" scope="page"/>
                <fmt:setLocale value="en-EN" scope="session"/>
                Today is
                <fmt:formatDate type="date" value="${now}" dateStyle="full"/>
            </div>
        </div>
        <br style="clear:both">
        <div class="card" style="padding: 0 5px 5px; float: bottom">
            <table class="table table-striped table-borderless .table-condensed" id="datatable">
                <thead style="background: #A4D349">
                <tr>
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
                        <td>${tEvent.treatmentType}</td>
                        <td class="text-right row">
                            <div style='margin-left:20px'>
                                <form:form
                                        action="${pageContext.request.contextPath}/nurse/treatment-event-details/${tEvent.treatmentEventId}"
                                        method="get">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Details"
                                            title="See details"
                                            style="background-color: yellowgreen">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </form:form>
                            </div>
                            <div style='margin-left:10px'>
                                <form:form
                                        action="${pageContext.request.contextPath}/nurse/treatment-event-set-completed"
                                        method="post">
                                    <input type="hidden" id="treatmentEventId" name="treatmentEventId"
                                           value="${tEvent.treatmentEventId}">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Completed"
                                            title='Change status to "Completed"'
                                            style="background-color: darkslategray">
                                        <i class="fas fa-check"></i>
                                    </button>
                                </form:form>
                            </div>
                            <div style='margin-left:10px'>
                                <button type="submit" id="cancel-button" name="cancel-button"
                                        class="btn btn-primary btn-sm"
                                        value="Cancel" style="background-color: darkred"
                                        title="Cancel treatment event"
                                        onclick="setVariable('${tEvent.treatmentEventId}')">
                                    <i class="fas fa-times"></i>
                                </button>
                            </div>
                        </td>
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
    <%@include file="shared/about_info_popup.jsp" %>
</div>
<%@include file="shared/cancel_t_event_popup.jsp" %>
<%@include file="shared/js_scripts.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/about_info_popup.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cancel_t_event_popup.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</body>
</html>
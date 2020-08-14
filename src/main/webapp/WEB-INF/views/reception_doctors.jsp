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
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/reception/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="show-med-record">
                    <a href="${pageContext.request.contextPath}/reception/edit-patient-data/${patientInfo.patientId}"
                       class="menu-btn">
                        <i class="far fa-edit"></i><span>Edit Patient</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh; background-image: url('/resources/images/icon_med_helper.png');
    background-size: contain; background-repeat: no-repeat; background-position: right">
        <div class="card" style="font-size: 16px; width: 60%; padding-left: 5px">
            <span class="details-title" style="font-weight: 500"> Available doctors:</span><br>
            <c:forEach items="${doctors}" var="doctor">
                <div class="row">
                    <div class="col-md-4">
                            ${doctor.name}
                    </div>
                    <div class="col-md-4">
                        <p>${doctor.position} </p>
                    </div>
                    <div class="col-md-2">
                        <p>${doctor.qualificationCategory} </p>
                    </div>
                    <div class="col-md-2">
                        <form:form action="${pageContext.request.contextPath}/reception/appoint-doctor" method="post">
                            <input type="hidden" name="patientId" value="${patient}">
                            <button type="submit" class="btn login_btn"
                                    style="background-color: darkorange; padding: 2px; align-self: center"
                                    name="doctorId"
                                    value="${doctor.employeeId}">
                                Appoint
                            </button>
                        </form:form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->
</body>
</html>

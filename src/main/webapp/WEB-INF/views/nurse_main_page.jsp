<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/nurse-avt.png" alt="">
                <p>Nurse</p>
            </center>
            <ul>
                <li class="item" id="events">
                    <a href="${pageContext.request.contextPath}/nurse/start-page" class="menu-btn">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="completedEvents">
                    <a href="${pageContext.request.contextPath}/nurse/show-completed-treatment-events" class="menu-btn">
                        <i class="fas fa-list-ol"></i><span>Completed</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh;">
        Treatment events
        <table class="table table-striped table-borderless .table-condensed " style="text-align: center">
            <thead class="thead-mine">
            <tr class="tr-mine">
                <th scope="col"></th>
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
            <c:forEach items="${treatmentEventList}" var="tEvent">
                <tr style=" padding: 0">
                    <td>#</td>
                    <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventTime}</td>
                    <td style="color: forestgreen; font-weight: bold">${tEvent.treatmentEventDate}</td>
                    <td>${tEvent.treatmentEventStatus}</td>
                    <td>${tEvent.patient}</td>
                    <td>${tEvent.medicineProcedureName}</td>
                    <td>${tEvent.treatmentType}</td>
                    <td class="text-right row">
                        <div style='margin-left:20px'>
                            <form action="${pageContext.request.contextPath}/nurse/treatment-event-details/${tEvent.treatmentEventId}"
                                  method="get">
                                <button type="submit" class="btn btn-primary btn-sm" value="Details"
                                        style="background-color: yellowgreen">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </form>
                        </div>
                        <div style='margin-left:10px'>
                            <form action="${pageContext.request.contextPath}/nurse/treatment-event-set-completed"
                                  method="post">
                                <input type="hidden" name="tEvent" value="${tEvent.treatmentEventId}">
                                <button type="submit" class="btn btn-primary btn-sm" value="Completed"
                                        style="background-color: darkslategray">
                                    <i class="fas fa-check"></i>
                                </button>
                            </form>
                        </div>
                        <div style='margin-left:10px'>
                            <c:set var="treatmentEventId" value="0" scope="page"/>
                            <button type="submit" id="cancel-button" name="cancel-button" class="btn btn-primary btn-sm"
                                    value="Cancel" style="background-color: darkred" onclick="">
                                <i class="fas fa-times"></i>
                            </button>
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
<%@include file="shared/cancel_t_event_popup.jsp" %>

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
<script type="text/javascript">
    $(document).ready(function () {
        $(".sidebar-btn").click(function () {
            $(".wrapper").toggleClass("collapse");
        });
    });

    document.getElementById('cancel-button').addEventListener('click', function () {
        document.querySelector(".popup").style.display = "flex";
    });

    document.getElementById('close-icon').addEventListener('click', function () {
        document.querySelector(".popup").style.display = "none";
    });
</script>
</body>
</html>
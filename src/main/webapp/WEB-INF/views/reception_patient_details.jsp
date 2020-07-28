<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png"
          type="image/png">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <title>MedHelper</title>
</head>
<body>
<div class="container-fluid">
    <h3>Patient Profile:</h3>
    <h4>${message}</h4>
    <h4>${patientInfo.surname}</h4>
    <h4>${patientInfo.firstName}</h4>
    <h4>${patientInfo.middleName}</h4>
    <h4>${patientInfo.gender}</h4>
    <h4>${patientInfo.dateOfBirth}</h4>
    <h4>${patientInfo.passportId}</h4>
    <h4>${patientInfo.address}</h4>
    <h4>${patientInfo.phoneNumber}</h4>
    <h4>${patientInfo.email}</h4>
    <h4>${patientInfo.insuranceCompany}</h4>
    <h4>${patientInfo.insurancePolicyCode}</h4>
    <c:choose>
        <c:when test="${patientInfo.attendingDoctorId==null}">
           <h4>Attending doctor: None yet</h4>
        </c:when>
        <c:otherwise>
            <h4>
                Attending doctor: ${patientInfo.attendingDoctorId.surname} ${patientInfo.attendingDoctorId.firstName},
                    ${patientInfo.attendingDoctorId.position}
            </h4>
        </c:otherwise>
    </c:choose>
    <form action="${pageContext.request.contextPath}/reception/appoint-doctor/" method="get">
        <input type="hidden" name="patientId" value="${patientInfo.patientId}">
        <button type="submit" class="btn btn-primary">Appoint a doctor</button>
    </form>
    <footer class="page-footer mdb-color pt-4" style="font-size: large">
        <p class="text-center text-md-left">
            © 2020 MedHelper. Professional hospital management system.
        </p>
    </footer>
</div>
</body>
</html>

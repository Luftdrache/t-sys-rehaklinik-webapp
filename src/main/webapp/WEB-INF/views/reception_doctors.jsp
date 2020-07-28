<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MedHelper</title>
</head>
<body>

All available doctors:

<c:forEach items="${doctors}" var="doctor">
    ${doctor.name} ${doctor.position} ${doctor.qualificationCategory}
    <form action="${pageContext.request.contextPath}/reception/appoint-doctor" method="post">
        <input type="hidden" name="patientId" value="${patient}">
        <button type="submit" class="btn login_btn" name="doctorId" value="${doctor.employeeId}">
            Appoint
        </button>
    </form>
</c:forEach>
</body>
</html>

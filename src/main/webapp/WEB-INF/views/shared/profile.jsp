<center class="profile" style="font-size: 20px">

    <sec:authorize access="hasRole('ADMIN')">
        <c:set var="role" scope="session" value="ADMIN"/>
        <img src="${pageContext.request.contextPath}/resources/images/admin-avt.png" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('RECEPTIONIST')">
        <c:set var="role" scope="session" value="RECEPTIONIST"/>
        <img src="${pageContext.request.contextPath}/resources/images/reception-avt-2.jpg" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('DOCTOR')">
        <c:set var="role" scope="session" value="DOCTOR"/>
        <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.jpg" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('NURSE')">
        <c:set var="role" scope="session" value="NURSE"/>
        <img src="${pageContext.request.contextPath}/resources/images/nurse-avt.png" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('PATIENT')">
        <c:set var="role" scope="session" value="PATIENT"/>
        <img src="${pageContext.request.contextPath}/resources/images/patient-avt.png" alt="">
    </sec:authorize>

    <c:choose>
        <c:when test="${role == 'PATIENT'}">
            <p><sec:authentication property="principal.patient.firstName"/> <sec:authentication
                    property="principal.patient.surname"/></p>
            <p><sec:authentication property="principal.patient.role"/></p>
        </c:when>
        <c:otherwise>
            <p><sec:authentication property="principal.employee.firstName"/> <sec:authentication
                    property="principal.employee.surname"/></p>
            <p><sec:authentication property="principal.employee.role"/></p>
        </c:otherwise>
    </c:choose>
</center>
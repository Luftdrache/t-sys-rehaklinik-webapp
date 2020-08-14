<center class="profile">
    <sec:authorize access="hasRole('ADMIN')">
        <img src="${pageContext.request.contextPath}/resources/images/admin-avt.png" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('RECEPTIONIST')">
        <img src="${pageContext.request.contextPath}/resources/images/reception-avt-2.jpg" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('DOCTOR')">
        <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.jpg" alt="">
    </sec:authorize>
    <sec:authorize access="hasRole('NURSE')">
        <img src="${pageContext.request.contextPath}/resources/images/nurse-avt.png" alt="">
    </sec:authorize>

    <p><sec:authentication property="principal.employee.firstName"/> <sec:authentication
            property="principal.employee.surname"/></p>
    <p><sec:authentication property="principal.employee.role"/></p>
</center>
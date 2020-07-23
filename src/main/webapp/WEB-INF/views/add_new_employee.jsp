<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 21.07.2020
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.QualificationCategories" %>
<%@ page import="com.tsystems.rehaklinik.types.Roles" %>

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
    <title>MedHelper</title>

    <style>
        body {
            background-color: #1d3d4e;
        }
    </style>

</head>
<body>
<div class="container-fluid">
    <div content="container" class="col-sm-7 col-sm-offset-4" style="background-color: #c9e9ff">
        <form action="${pageContext.request.contextPath}/admin/add-employee" modelAttribute="addEmployee" method="post"
              class="form-horizontal"
              role="form">
            <div style="padding-left: 10%">
                <h2>Add New Employee</h2>
                <span class="help-block">*Required fields</span>
            </div>
            <div class="form-group">
                <label for="firstName" class="col-sm-4 control-label">First Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="firstName" name="firstName" placeholder="First Name" class="form-control"
                           autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="middleName" class="col-sm-4 control-label">Second Name</label>
                <div class="col-sm-5">
                    <input type="text" id="middleName" name="middleName" placeholder="Second Name" class="form-control"
                           autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-4 control-label">Last Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="surname" name="surname" placeholder="Last Name" class="form-control"
                           autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-4 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" id="login" name="authenticationDataEmployee.login" placeholder="Login"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">Password*</label>
                <div class="col-sm-5">
                    <input type="password" id="password" name="authenticationDataEmployee.password"
                           placeholder="Password" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="dateOfBirth" class="col-sm-4 control-label">Date of Birth*</label>
                <div class="col-sm-5">
                    <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="passportId" class="col-sm-4 control-label">Passport Id*</label>
                <div class="col-sm-5">
                    <input type="text" id="passportId" name="passportId" placeholder="Passport Id" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-4 control-label">Home Address*</label>
                <div class="col-sm-5">
                    <input type="text" id="address" name="address" placeholder="Home Address" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="phoneNumber" class="col-sm-4 control-label">Phone*</label>
                <div class="col-sm-5">
                    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-4 control-label">Email</label>
                <div class="col-sm-5">
                    <input type="email" id="email" placeholder="Email" name="email" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="position" class="col-sm-4 control-label">Position*</label>
                <div class="col-sm-5">
                    <input type="text" id="position" name="position.positionName" placeholder="Position"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
<%--                <c:set var="qualificationCategoryIdChoice" value="1"/>--%>
                <label for="qualificationCategory" class="col-sm-4 control-label">Qualification category</label>
                <div class="col-sm-5">
                    <select id="qualificationCategory" name="qualificationCategory.qualificationCategoryName"
                            class="form-control" onchange="setQCategoryId()">
                        <c:forEach items="${QualificationCategories.values()}" var="qCategory">
                            <option selected>${qCategory.toString()}</option>
                        </c:forEach>
                    </select>
<%--                    <input type="number" hidden name="qualificationCategory.qualificationCategoryId"--%>
<%--                           value=${qualificationCategoryIdChoice}>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="officeOrWardNumber" class="col-sm-4 control-label">Doctor's office or ward number</label>
                <div class="col-sm-5">
                    <input type="number" id="officeOrWardNumber" name="officeOrWardNumber"
                           placeholder="Doctor's office or ward number"
                           class="form-control" value="0" min="0">
                </div>
            </div>
            <div class="form-group">
                <label for="role2" class="col-sm-4 control-label">ROLE*</label>
                <div class="col-sm-5">
                    <select id="role2" name="role.roleName" class="form-control">
                        <c:forEach items="${Roles.values()}" var="role">
                            <c:if test="${role != 'PATIENT'}">
                                <option selected>${role.toString()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="submit" class="btn login_btn" value="Add New Employee"
                   style="background-color: orange; opacity: 0.9;"/>
        </form>
        <div>
            <h4> ${message}</h4>
            <h5>${newEmployee.toString()}</h5>
        </div>
    </div>
</div>

<%--<script type="text/javascript">--%>
<%--    function setQCategoryId() {--%>
<%--        var qCategoryId = document.getElementById("qualificationCategory").selectedIndex;--%>
<%--        alert(qCategoryId);--%>
<%--        ${qualificationCategoryIdChoice} = qCategoryId;--%>
<%--    }--%>
<%--</script>--%>

</body>
</html>



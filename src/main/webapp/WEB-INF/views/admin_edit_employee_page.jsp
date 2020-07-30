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
        <form action="${pageContext.request.contextPath}/admin/edit" method="post" class="form-horizontal" role="form">
            <div style="padding-left: 10%">
                <h2>Edit Employee</h2>
                <span class="help-block">*Required fields</span>
            </div>
            <input type="hidden" id="employeeId" name="employeeId" value="${employeeToEdit.employeeId}" />
            <div class="form-group">
                <label for="firstName" class="col-sm-4 control-label">First Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="firstName" name="firstName" value="${employeeToEdit.firstName}" placeholder="First Name" class="form-control"
                           autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                <div class="col-sm-5">
                    <input type="text" id="middleName" value="${employeeToEdit.middleName}"  name="middleName"
                           placeholder="Middle Name" class="form-control" autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-4 control-label">Last Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="surname" name="surname" value="${employeeToEdit.surname}"   placeholder="Last Name" class="form-control"
                           autofocus>
                </div>
            </div>

            <input type="hidden" id="authenticationDataEmployee" name="authenticationDataEmployee.authenticationDataId"
                   value="${employeeToEdit.authenticationDataEmployee.authenticationDataId}" />

            <div class="form-group">
                <label for="login" class="col-sm-4 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" id="login" value="${employeeToEdit.authenticationDataEmployee.login}" name="authenticationDataEmployee.login" placeholder="Login"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">Password*</label>
                <div class="col-sm-5">
                    <input type="password" id="password"  value="${employeeToEdit.authenticationDataEmployee.password}"
                           name="authenticationDataEmployee.password" placeholder="Password" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="dateOfBirth" class="col-sm-4 control-label">Date of Birth*</label>
                <div class="col-sm-5">
                    <input type="date" id="dateOfBirth" value="${employeeToEdit.dateOfBirth}"
                           name="dateOfBirth" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="passportId" class="col-sm-4 control-label">Passport Id*</label>
                <div class="col-sm-5">
                    <input type="text" id="passportId" value="${employeeToEdit.passportId}" name="passportId" placeholder="Passport Id" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-4 control-label">Home Address*</label>
                <div class="col-sm-5">
                    <input type="text" id="address" name="address" value="${employeeToEdit.address}" placeholder="Home Address" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="phoneNumber" class="col-sm-4 control-label">Phone*</label>
                <div class="col-sm-5">
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${employeeToEdit.phoneNumber}" placeholder="Phone" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-4 control-label">Email</label>
                <div class="col-sm-5">
                    <input type="email" id="email" placeholder="Email" name="email" value="${employeeToEdit.email}" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="position" class="col-sm-4 control-label">Position*</label>
                <div class="col-sm-5">
                    <input type="text" id="position" name="position"
                           value="${employeeToEdit.position}" placeholder="Position"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="qualificationCategory" class="col-sm-4 control-label">Qualification category</label>
                <div class="col-sm-5">
                    <select id="qualificationCategory" name="qualificationCategory"
                            value="${employeeToEdit.qualificationCategory}" class="form-control">
                        <c:forEach items="${QualificationCategories.values()}" var="qCategory">
                            <option>${qCategory.toString()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="officeOrWardNumber" class="col-sm-4 control-label">Doctor's office or ward number</label>
                <div class="col-sm-5">
                    <input type="number" id="officeOrWardNumber" name="officeOrWardNumber"
                           placeholder="Doctor's office or ward number"
                           value="${employeeToEdit.officeOrWardNumber}"
                           class="form-control" min="0">
                </div>
            </div>
            <div class="form-group">
                <label for="role" class="col-sm-4 control-label">ROLE*</label>
                <div class="col-sm-5">
                    <select id="role" name="role" value="${employeeToEdit.role}" class="form-control">
                        <c:forEach items="${Roles.values()}" var="role">
                            <c:if test="${role != 'PATIENT'}">
                                <option>${role.toString()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="submit" class="btn login_btn" value="Edit"
                   style="background-color: orange; opacity: 0.9;"/>
        </form>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 26.07.2020
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tsystems.rehaklinik.types.Roles" %>
<%@ page import="com.tsystems.rehaklinik.types.Gender" %>


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
        <form action="${pageContext.request.contextPath}/reception/add-patient" modelAttribute="addPatient" method="post"
              class="form-horizontal"
              role="form">
            <div style="padding-left: 10%">
                <h2>Add Patient</h2>
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
                <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                <div class="col-sm-5">
                    <input type="text" id="middleName" name="middleName" placeholder="Middle Name" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-4 control-label">Last Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="surname" name="surname" placeholder="Last Name" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="gender" class="col-sm-4 control-label">Gender*</label>
                <div class="col-sm-5">
                    <select id="gender" name="gender"
                            class="form-control">
                        <c:forEach items="${Gender.values()}" var="gen">
                            <option selected>${gen.toString()}</option>
                        </c:forEach>
                    </select>
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
                <label for="insurancePolicyCode" class="col-sm-4 control-label">Insurance Policy Code*</label>
                <div class="col-sm-5">
                    <input type="text" id="insurancePolicyCode" placeholder="Insurance Policy Code"
                           name="insurancePolicyCode" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="insuranceCompany" class="col-sm-4 control-label">Insurance Company*</label>
                <div class="col-sm-5">
                    <input type="text" id="insuranceCompany" placeholder="Insurance Company"
                           name="insuranceCompany" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="consentToPersonalDataProcessing" class="col-sm-4 control-label"></label>
                <div class="col-sm-5">
                    <input type="checkbox" id="consentToPersonalDataProcessing"
                           name="consentToPersonalDataProcessing" class="form-control">Consent To Personal Data Processing*</input>
                </div>
            </div>
<%--            *****HIDDEN:*****--%>
            <input type="hidden" id="role" name="role" value="PATIENT">
            <input type="hidden" id="medicalRecord" name="medicalRecord.hospitalStayStatus" value="NEW">
<%--            *****HIDDEN:*****--%>
            <div class="form-group">
                <label for="login" class="col-sm-4 control-label">Login</label>
                <div class="col-sm-5">
                    <input type="text" id="login" name="authenticationDataPatient.login" placeholder="Login"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">Password</label>
                <div class="col-sm-5">
                    <input type="password" id="password" name="authenticationDataPatient.password"
                           placeholder="Password" class="form-control">
                </div>
            </div>
            <input type="submit" class="btn login_btn" value="Add Patient"
                   style="background-color: orange; opacity: 0.9;"/>
        </form>
    </div>
</div>
</body>
</html>
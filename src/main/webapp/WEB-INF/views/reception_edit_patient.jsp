<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.Gender" %>
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
    <div content="container" class="col-sm-8 col-sm-offset-2" style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
        <form action="${pageContext.request.contextPath}/reception/edit" method="post" class="form-horizontal" role="form">
            <div style="padding-left: 10%">
                <h2>Edit Patient Data</h2>
                <span class="help-block">*Required fields</span>
            </div>
            <input type="hidden" id="patientId" name="patientId" value="${patientToEdit.patientId}" />
            <div class="form-group">
                <label for="firstName" class="col-sm-4 control-label">First Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="firstName" name="firstName" value="${patientToEdit.firstName}"
                           placeholder="First Name" class="form-control" autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                <div class="col-sm-5">
                    <input type="text" id="middleName" value="${patientToEdit.middleName}"
                           name="middleName" placeholder="Middle Name" class="form-control" autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-4 control-label">Last Name*</label>
                <div class="col-sm-5">
                    <input type="text" id="surname" name="surname" value="${patientToEdit.surname}"
                           placeholder="Last Name" class="form-control" autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="gender" class="col-sm-4 control-label">Gender*</label>
                <div class="col-sm-5">
                    <select id="gender" name="gender" velue="${patientToEdit.gender}"
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
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${patientToEdit.dateOfBirth}" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="passportId" class="col-sm-4 control-label">Passport Id*</label>
                <div class="col-sm-5">
                    <input type="text" id="passportId" name="passportId" value="${patientToEdit.passportId}"
                           placeholder="Passport Id" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-4 control-label">Home Address*</label>
                <div class="col-sm-5">
                    <input type="text" id="address" name="address" value="${patientToEdit.address}"
                           placeholder="Home Address" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="phoneNumber" class="col-sm-4 control-label">Phone*</label>
                <div class="col-sm-5">
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${patientToEdit.phoneNumber}"
                           placeholder="Phone" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-4 control-label">Email</label>
                <div class="col-sm-5">
                    <input type="email" id="email" name="email" value="${patientToEdit.email}" placeholder="Email" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="insurancePolicyCode" class="col-sm-4 control-label">Insurance Policy Code*</label>
                <div class="col-sm-5">
                    <input type="text" id="insurancePolicyCode" value="${patientToEdit.insurancePolicyCode}"
                           placeholder="Insurance Policy Code" name="insurancePolicyCode" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="insuranceCompany" class="col-sm-4 control-label">Insurance Company*</label>
                <div class="col-sm-5">
                    <input type="text" id="insuranceCompany" value="${patientToEdit.insuranceCompany}"
                           placeholder="Insurance Company" name="insuranceCompany" class="form-control">
                </div>
            </div>
            <%--            *****HIDDEN:*****--%>
            <input type="hidden" id="consentToPersonalDataProcessing"
                   name="consentToPersonalDataProcessing" value="${patientToEdit.consentToPersonalDataProcessing}"/>
            <input type="hidden" id="role" name="role" value="${patientToEdit.role}">
            <input type="hidden" id="medicalRecord" name="medicalRecord.medicalRecordId"
                   value="${patientToEdit.medicalRecord.medicalRecordId}">
            <input type="hidden" id="medicalRecord" name="medicalRecord.hospitalStayStatus"
                   value="${patientToEdit.medicalRecord.hospitalStayStatus}">
            <input type="hidden" id="authenticationDataPatient" name="authenticationDataPatient.authenticationDataId"
                   value="${patientToEdit.authenticationDataPatient.authenticationDataId}" />
            <%--            *****HIDDEN:*****--%>
            <div class="form-group">
                <label for="login" class="col-sm-4 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" id="login" value="${patientToEdit.authenticationDataPatient.login}"
                           name="authenticationDataPatient.login" placeholder="Login"
                           class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">Password*</label>
                <div class="col-sm-5">
                    <input type="password" id="password"  value="${patientToEdit.authenticationDataPatient.password}"
                           name="authenticationDataPatient.password" placeholder="Password" class="form-control">
                </div>
            </div>
            <div style="padding-left: 50%">
            <input type="submit" class="btn login_btn" value="Edit"
                   style="background-color: orange; opacity: 0.9;"/>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/reception/start-page" method="get">
            <button type="submit" class="btn btn-primary">To main page</button>
        </form>
    </div>
</div>
<footer class="page-footer mdb-color pt-4" style="font-size: small; color: #c9e9ff">
    <p class="text-center text-md-left">
        © 2020 MedHelper. Professional hospital management system.
    </p>
</footer>
</body>
</html>

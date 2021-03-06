<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.Gender" %>


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
    <!-- form style-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form_style.css">
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
                <li class="item" id="#employees" style="font-size: 20px">
                    <a href="${pageContext.request.contextPath}/reception/start-page" class="menu-btn">
                        <i class="fas fa-users"></i><span>Patients</span>
                    </a>
                </li>
                <li class="item" id="#prescriptions" style="font-size: 20px">
                    <a href="${pageContext.request.contextPath}/reception/add-patient" class="menu-btn">
                        <i class="fas fa-user-plus"></i><span>Add new</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; height: auto">
        <div class="container-fluid">
            <div content="container" class="col-sm-8 col-sm-offset-2"
                 style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
                <form:form action="${pageContext.request.contextPath}/reception/add-patient"
                           method="post"
                           class="form-horizontal"
                           role="form">
                    <div style="padding-left: 10%">
                        <h2>Add Patient</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-sm-4 control-label">First Name*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="firstName" name="firstName" placeholder="First Name"
                                   oninvalid="this.setCustomValidity('Please enter a first name')"
                                   oninput="setCustomValidity('')"
                                   class="form-control"
                                   autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                        <div class="col-sm-5">
                            <input type="text" id="middleName" name="middleName" placeholder="Middle Name"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="col-sm-4 control-label">Surname*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="surname" name="surname" placeholder="Surname"
                                   oninvalid="this.setCustomValidity('Please enter a surname')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
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
                            <input required type="date" id="dateOfBirth" name="dateOfBirth"
                                   min="1900-01-01"
                                   oninvalid="this.setCustomValidity('Please set a date of birth ' +
                                    'Date of birth must not be less than \'1900-01-01\'')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passportId" class="col-sm-4 control-label">Passport Id*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="passportId" name="passportId"
                                   placeholder="Passport Id"
                                   oninvalid="this.setCustomValidity('Please enter a passport id')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-4 control-label">Home Address*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="address" name="address" placeholder="Home Address"
                                   oninvalid="this.setCustomValidity('Please enter an address')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber" class="col-sm-4 control-label">Phone*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone"
                                   oninvalid="this.setCustomValidity('Please enter a phone number')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
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
                            <input required type="text" id="insurancePolicyCode"
                                   placeholder="Insurance Policy Code"
                                   oninvalid="this.setCustomValidity('Please enter an insurance policy code')"
                                   oninput="setCustomValidity('')"
                                   name="insurancePolicyCode" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="insuranceCompany" class="col-sm-4 control-label">Insurance Company*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="insuranceCompany"
                                   placeholder="Insurance Company"
                                   oninvalid="this.setCustomValidity('Please enter an insurance company name')"
                                   oninput="setCustomValidity('')"
                                   name="insuranceCompany" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="consentToPersonalDataProcessing" class="col-sm-4 control-label"></label>
                        <div class="col-sm-5">
                            <input required type="checkbox" id="consentToPersonalDataProcessing"
                                   oninvalid="this.setCustomValidity('Please give your consent to personal data processing')"
                                   oninput="setCustomValidity('')"
                                   name="consentToPersonalDataProcessing">
                            Consent To Personal Data Processing*
                        </div>
                    </div>
                    <%--            *****HIDDEN:*****--%>
                    <input type="hidden" id="role" name="role" value="PATIENT">
                    <input type="hidden" id="medicalRecord" name="medicalRecord.hospitalStayStatus" value="NEW">
                    <%--            *****HIDDEN:*****--%>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">Username*</label>
                        <div class="col-sm-5">
                            <input required type="text" id="username" name="authenticationDataPatient.username"
                                   placeholder="Username"
                                   oninvalid="this.setCustomValidity('Please enter a username')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-4 control-label">Password*</label>
                        <div class="col-sm-5">
                            <input required type="password" id="password" name="authenticationDataPatient.password"
                                   placeholder="Password"
                                   oninvalid="this.setCustomValidity('Please enter a password')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div style="padding-left: 50%">
                        <input type="submit" class="btn login_btn" value="Add Patient"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->

</div>
<!--wrapper end-->

</body>
</html>
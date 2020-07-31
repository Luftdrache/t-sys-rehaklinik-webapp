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
    <!--header menu start-->
    <div class="header">
        <div class="header-menu">
            <div class="title">Med<span>Helper</span></div>
            <div class="sidebar-btn">
                <i class="fas fa-bars"></i>
            </div>
            <ul>
                <li><input type="button" value="Sign out" class="btn login_btn"
                           style="background-color: orange; padding: 5px; margin-top: 15px"></li>
            </ul>
        </div>
    </div>
    <!--header menu end-->
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/reception-avt.jpg" alt="">
                <p style="font-size: 20px">Receptionist</p>
            </center>
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
            <li class="item" id="#settings" style="font-size: 20px">
                <a href="#settings" class="menu-btn">
                    <i class="fas fa-cog"></i><span>Settings <i class="fas fa-chevron-down drop-down"></i></span>
                </a>
                <div class="sub-menu">
                    <a href="#"><i class="fas fa-lock"></i><span>Password</span></a>
                    <a href="#"><i class="fas fa-language"></i><span>Language</span></a>
                </div>
            </li>
            <li class="item" style="font-size: 20px">
                <a href="#" class="menu-btn">
                    <i class="fas fa-info-circle"></i><span>About</span>
                </a>
            </li>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; height: auto">
        <div class="container-fluid">
            <div content="container" class="col-sm-8 col-sm-offset-2"
                 style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
                <form action="${pageContext.request.contextPath}/reception/edit" method="post" class="form-horizontal"
                      role="form">
                    <div style="padding-left: 10%">
                        <h2>Edit Patient Data</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <input type="hidden" id="patientId" name="patientId" value="${patientToEdit.patientId}"/>
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
                                    <option>${gen.toString()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dateOfBirth" class="col-sm-4 control-label">Date of Birth*</label>
                        <div class="col-sm-5">
                            <input type="date" id="dateOfBirth" name="dateOfBirth" value="${patientToEdit.dateOfBirth}"
                                   class="form-control">
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
                            <input type="email" id="email" name="email" value="${patientToEdit.email}"
                                   placeholder="Email"
                                   class="form-control">
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
                           name="consentToPersonalDataProcessing"
                           value="${patientToEdit.consentToPersonalDataProcessing}"/>
                    <input type="hidden" id="role" name="role" value="${patientToEdit.role}">
                    <input type="hidden" id="medicalRecord" name="medicalRecord.medicalRecordId"
                           value="${patientToEdit.medicalRecord.medicalRecordId}">
                    <input type="hidden" id="medicalRecord" name="medicalRecord.hospitalStayStatus"
                           value="${patientToEdit.medicalRecord.hospitalStayStatus}">
                    <input type="hidden" id="authenticationDataPatient"
                           name="authenticationDataPatient.authenticationDataId"
                           value="${patientToEdit.authenticationDataPatient.authenticationDataId}"/>
                    <input type="hidden" id="attendingDoctorId" name="attendingDoctorId.employeeId"
                           value="${patientToEdit.attendingDoctorId.employeeId}">
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
                            <input type="password" id="password"
                                   value="${patientToEdit.authenticationDataPatient.password}"
                                   name="authenticationDataPatient.password" placeholder="Password"
                                   class="form-control">
                        </div>
                    </div>
                    <div style="padding-left: 50%">
                        <input type="submit" class="btn login_btn" value="Edit"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->
</body>
</html>

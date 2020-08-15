<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.QualificationCategories" %>
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
    <%@include file="shared/shared_header.jsp" %>
    <!--header menu end-->
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#employees" style="font-size: 20px">
                    <a href="${pageContext.request.contextPath}/admin/start-page" class="menu-btn">
                        <i class="fas fa-users"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="#prescriptions" style="font-size: 20px">
                    <a href="${pageContext.request.contextPath}/admin/add-employee" class="menu-btn">
                        <i class="fas fa-user-plus"></i><span>Add new</span>
                    </a>
                </li>
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
                <form:form action="${pageContext.request.contextPath}/admin/add-employee"
                           method="post"
                           class="form-horizontal"
                           role="form">
                    <div style="padding-left: 10%">
                        <h2>Add New Employee</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-sm-4 control-label">First Name*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="firstName" name="firstName" placeholder="First Name"
                                   min=3
                                   oninvalid="this.setCustomValidity('Please enter a first name')"
                                   oninput="setCustomValidity('')"
                                   class="form-control"
                                   autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                        <div class="col-sm-6">
                            <input type="text" id="middleName" name="middleName" placeholder="Middle Name"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="col-sm-4 control-label">Surname*</label>
                        <div class="col-sm-6">
                            <input required
                                   type="text" id="surname" name="surname" placeholder="Surname"
                                   oninvalid="this.setCustomValidity('Please enter a surname')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">Username*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="username" name="authenticationDataEmployee.username"
                                   placeholder="Username"
                                   oninvalid="this.setCustomValidity('Please enter a username')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-4 control-label">Password*</label>
                        <div class="col-sm-6">
                            <input required type="password" id="password" name="authenticationDataEmployee.password"
                                   oninvalid="this.setCustomValidity('Please enter a password')"
                                   oninput="setCustomValidity('')"
                                   placeholder="Password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dateOfBirth" class="col-sm-4 control-label">Date of Birth*</label>
                        <div class="col-sm-6">
                            <input required type="date" id="dateOfBirth" name="dateOfBirth" min="1920-01-01"
                                   max="2010-01-01"
                                   oninvalid="this.setCustomValidity('Please enter a date of birth')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passportId" class="col-sm-4 control-label">Passport Id*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="passportId" name="passportId" placeholder="Passport Id"
                                   oninvalid="this.setCustomValidity('Please enter a passport id')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-4 control-label">Home Address*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="address" name="address" placeholder="Home Address"
                                   oninvalid="this.setCustomValidity('Please enter an address')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber" class="col-sm-4 control-label">Phone*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone"
                                   oninvalid="this.setCustomValidity('Please enter a phone number')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-4 control-label">Email</label>
                        <div class="col-sm-6">
                            <input type="email" id="email" placeholder="Email"
                                   name="email" value="@rehaclinic.com"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-4 control-label">Position*</label>
                        <div class="col-sm-6">
                            <input required type="text" id="position" name="position" placeholder="Position"
                                   oninvalid="this.setCustomValidity('Please enter a position')"
                                   oninput="setCustomValidity('')"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qualificationCategory" class="col-sm-4 control-label">Qualification category</label>
                        <div class="col-sm-6">
                            <select id="qualificationCategory" name="qualificationCategory"
                                    class="form-control">
                                <c:forEach items="${QualificationCategories.values()}" var="qCategory">
                                    <option selected>${qCategory.toString()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="officeOrWardNumber" class="col-sm-4 control-label">Doctor's office or ward
                            number</label>
                        <div class="col-sm-6">
                            <input type="number" id="officeOrWardNumber" name="officeOrWardNumber"
                                   placeholder="Doctor's office or ward number"
                                   class="form-control" value="0" min="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role" class="col-sm-4 control-label">ROLE*</label>
                        <div class="col-sm-6">
                            <select required id="role" name="role" class="form-control">
                                <c:forEach items="${Roles.values()}" var="role">
                                    <c:if test="${role != 'PATIENT'}">
                                        <option selected>${role.toString()}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="padding-left: 40%">
                        <input type="submit" class="btn login_btn" value="Add New Employee"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form:form>
                <%--                <div>--%>
                <%--                    <h4> ${message}</h4>--%>
                <%--                    <h5>${newEmployee.toString()}</h5>--%>
                <%--                </div>--%>
            </div>
        </div>

    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.TreatmentType" %>
<html lang="en">
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
<c:set var="docId" scope="page"><sec:authentication
        property="principal.employee.employeeId"/></c:set>
<!--wrapper start-->
<div class="wrapper">
    <%@include file="shared/shared_header.jsp" %>
    <!--sidebar start-->
    <div class="sidebar">
        <div class="sidebar-menu">
            <%@include file="shared/profile.jsp" %>
            <ul>
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn"
                       style="font-size: 20px;">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="show-med-record" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${patientId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="background-color: #DEF0FF; min-height: 90vh; height: auto">
        <div class="container-fluid">
            <div content="container" class="col-sm-8 col-sm-offset-2"
                 style="background-color: #c9e9ff; margin-top: 10px; border-radius: 20px">
                <c:if test="${docId ne attendingDoctorId}">
                    <div style="color: indianred; font-weight: bold; background-color: #fff">
                        <h3>Sorry, you are not allowed to make changes for this patient</h3>
                    </div>
                </c:if>
                <c:if test="${docId eq attendingDoctorId}">
                    <form:form action="${pageContext.request.contextPath}/doctor/add-prescription" method="post"
                               class="form-horizontal"
                               id="myForm"
                               role="form"
                               lang="en">
                        <div style="padding-left: 20%">
                            <h2>Add Prescription</h2>
                            <span class="help-block">*Required fields</span>
                        </div>
                        <div class="form-group">
                            <label for="medicineProcedureName" class="col-sm-4 control-label">Name*</label>
                            <div class="col-sm-6">
                                <input required type="text" id="medicineProcedureName"
                                       name="medicineAndProcedure.medicineProcedureName"
                                       placeholder="Name"
                                       value="${sessionScope.prescriptionInputData.medicineAndProcedure.medicineProcedureName}"
                                       oninvalid="this.setCustomValidity('Please enter a medicine or procedure name')"
                                       oninput="setCustomValidity('')"
                                       class="form-control" autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="treatmentType" class="col-sm-4 control-label">Treatment Type*</label>
                            <div class="col-sm-6">
                                <select id="treatmentType" name="medicineAndProcedure.treatmentType"
                                        class="form-control">
                                    <c:forEach items="${TreatmentType.values()}" var="type">
                                        <option selected>${type.toString()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dose" class="col-sm-4 control-label">Dose</label>
                            <div class="col-sm-6">
                                <input type="text" id="dose"
                                       name="dose"
                                       placeholder="Dose"
                                       class="form-control"
                                       value="${sessionScope.prescriptionInputData.dose}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="administeringMedicationMethod" class="col-sm-4 control-label">Administering
                                Medication Method</label>
                            <div class="col-sm-6">
                                <input type="text" id="administeringMedicationMethod"
                                       name="administeringMedicationMethod"
                                       placeholder="Administering Medication Method" class="form-control"
                                       value="${sessionScope.prescriptionInputData.administeringMedicationMethod}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startTreatment" class="col-sm-4 control-label">Start Treatment*</label>
                            <div class="col-sm-6">
                                <input required type="date" id="startTreatment" name="startTreatment"
                                       min=""
                                       oninvalid="this.setCustomValidity('Please set a start treatment date')"
                                       oninput="setCustomValidity('')"
                                       class="form-control"
                                       value="${sessionScope.prescriptionInputData.startTreatment}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endTreatment" class="col-sm-4 control-label">End Treatment*</label>
                            <div class="col-sm-6">
                                <input required type="date" id="endTreatment" name="endTreatment"
                                       min=""
                                       max="2021-08-16"
                                       class="form-control"
                                       oninvalid="this.setCustomValidity('Please set an end treatment date')"
                                       oninput="setCustomValidity('')"
                                       value="${sessionScope.prescriptionInputData.endTreatment}">
                            </div>
                        </div>
                        <input type="hidden" id="patient"
                               name="patient.patientId" value="${patientId}"/>
                        <div class="form-group">
                            <label for="intervalInHours" class="col-sm-4 control-label">Interval in Hours</label>
                            <div class="col-sm-6">
                                <input type="number" id="intervalInHours" name="treatmentTimePattern.intervalInHours"
                                       placeholder="Interval In Hours" class="form-control"
                                       value="0" min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="precisionTime" class="col-sm-4 control-label">Start Interval/precision
                                Time</label>
                            <div class="col-sm-6">
                                <input type="time" id="precisionTime" name="treatmentTimePattern.precisionTime"
                                       class="form-control"
                                       value="${sessionScope.prescriptionInputData.treatmentTimePattern.precisionTime}">
                            </div>
                        </div>
                        <br>
                        <div class="col-sm-offset-2"><p style="color: #285e8e; font-weight: 700">DAYS:</p></div>

                        <div class="form-group">
                            <label for="Sunday" class="col-sm-4 control-label">Sunday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Sunday"
                                       name="treatmentTimePattern.Sunday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Monday" class="col-sm-4 control-label">Monday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Monday"
                                       name="treatmentTimePattern.Monday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Tuesday" class="col-sm-4 control-label">Tuesday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Tuesday"
                                       name="treatmentTimePattern.Tuesday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Wednesday" class="col-sm-4 control-label">Wednesday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Wednesday"
                                       name="treatmentTimePattern.Wednesday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Thursday" class="col-sm-4 control-label">Thursday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Thursday"
                                       name="treatmentTimePattern.Thursday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Friday" class="col-sm-4 control-label">Friday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Friday"
                                       name="treatmentTimePattern.Friday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Saturday" class="col-sm-4 control-label">Saturday</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="Saturday"
                                       name="treatmentTimePattern.Saturday">
                            </div>
                        </div>

                        <br>
                        <div class="col-sm-offset-2"><p style="color: #285e8e; font-weight: 700">ADDITIONAL:</p></div>
                        <div class="form-group">
                            <label for="beforeMeals" class="col-sm-4 control-label">Before Meals</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="beforeMeals"
                                       name="treatmentTimePattern.beforeMeals">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="atMeals" class="col-sm-4 control-label">At Meals</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="atMeals"
                                       name="treatmentTimePattern.atMeals">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="afterMeals" class="col-sm-4 control-label">After Meals</label>
                            <div class="col-sm-6">
                                <input type="checkbox" id="afterMeals"
                                       name="treatmentTimePattern.afterMeals">
                            </div>
                        </div>

                        <div style="padding-left: 50%">
                            <input type="submit" class="btn login_btn"
                                   id="primaryButton"
                                   value="Add prescription"
                                   style="background-color: orange; opacity: 0.9;"/>
                        </div>
                    </form:form>
                </c:if>
            </div>
            <div class="warning-message" style="float: right;  display: none;">
                <div class="card" style="width: 200px; color: indianred; font-weight: bold;margin-top: 10%">
                    <br>
                    <h4>The patient already has a treatment event for this time.</h4>
                    <br>
                    <h4>Are you sure you want to add another one?</h4>
                </div>
            </div>
            <!-- *******MAIN CONTAINER******* -->
        </div>
        <c:set var="check" value='${sessionScope.isShowWarningPopup}' scope="page"></c:set>
        <script>
            function checkExistedPrescriptions() {
                document.querySelector(".warning-message").style.display = "flex";
            }

            function hideWarning() {
                document.querySelector(".warning-message").style.display = "none";
            }
        </script>
        <script src="${pageContext.request.contextPath}/resources/js/check_end_date.js"></script>
        <c:choose>
            <c:when test="${check eq 'true'}">
                <script> checkExistedPrescriptions() </script>
            </c:when>
        </c:choose>

        <!--wrapper end-->
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/today_date.js"></script>
</body>
</html>


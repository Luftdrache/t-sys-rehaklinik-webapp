<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.tsystems.rehaklinik.types.TreatmentType" %>
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
                <li class="item" id="#patients">
                    <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn"
                       style="font-size: 20px;">
                        <i class="fas fa-clinic-medical"></i><span>Main page</span>
                    </a>
                </li>
                <li class="item" id="show-med-record" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/medical-record/${prescriptionToEdit.patientId}"
                       class="menu-btn">
                        <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                    </a>
                </li>
                <li class="item" id="add-prescription" style="font-size: 20px;">
                    <a href="${pageContext.request.contextPath}/doctor/add-prescription/${prescriptionToEdit.patientId}" class="menu-btn">
                        <i class="fas fa-tablets"></i><span>Add prescription</span>
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
                <form:form action="${pageContext.request.contextPath}/doctor/edit-prescription" method="post"
                           class="form-horizontal"
                           role="form">
                    <div style="padding-left: 20%">
                        <h2>Edit Prescription</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <!-- HIDDEN -->
                    <input type="hidden" id="prescriptionId" name="prescriptionId"
                           value="${prescriptionToEdit.prescriptionId}"/>
                    <input type="hidden" id="medicineProcedureId" name="medicineProcedureId"
                           value="${prescriptionToEdit.medicineProcedureId}"/>
                    <input type="hidden" id="treatmentTimePatternId" name="treatmentTimePatternId"
                           value="${prescriptionToEdit.treatmentTimePatternId}"/>
                    <input type="hidden" id="patientId" name="patientId"
                           value="${prescriptionToEdit.patientId}"/>
                    <!-- HIDDEN -->

                    <div class="form-group">
                        <label for="medicineProcedureName" class="col-sm-4 control-label">Medicine/Procedure
                            Name*</label>
                        <div class="col-sm-6">
                            <input type="text" id="medicineProcedureName"
                                   value="${prescriptionToEdit.medicineProcedureName}"
                                   name="medicineProcedureName" placeholder="Medicine/Procedure Name"
                                   class="form-control"
                                   autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="treatmentType" class="col-sm-4 control-label">Treatment Type*</label>
                        <div class="col-sm-6">
                            <select id="treatmentType" name="treatmentType"
                                    value="${prescriptionToEdit.treatmentType}"
                                    class="form-control">
                                <c:forEach items="${TreatmentType.values()}" var="type">
                                    <c:if test="${type ne prescriptionToEdit.treatmentType}">
                                        <option>${type.toString()}</option>
                                    </c:if>
                                </c:forEach>
                                <option selected>${prescriptionToEdit.treatmentType.toString()}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dose" class="col-sm-4 control-label">Dose</label>
                        <div class="col-sm-6">
                            <input type="text" id="dose" value="${prescriptionToEdit.dose}"
                                   name="dose" placeholder="Dose" class="form-control"
                                   autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="administeringMedicationMethod" class="col-sm-4 control-label">Administering
                            Medication Method</label>
                        <div class="col-sm-6">
                            <input type="text" id="administeringMedicationMethod"
                                   value="${prescriptionToEdit.administeringMedicationMethod}"
                                   name="administeringMedicationMethod" placeholder="Administering Medication Method"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startTreatment" class="col-sm-4 control-label">Start Treatment</label>
                        <div class="col-sm-6">
                            <input type="date" id="startTreatment" value="${prescriptionToEdit.startTreatment}"
                                   name="startTreatment" placeholder="Start Treatment" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="endTreatment" class="col-sm-4 control-label">End Treatment</label>
                        <div class="col-sm-6">
                            <input type="date" id="endTreatment" value="${prescriptionToEdit.endTreatment}"
                                   name="endTreatment" placeholder="End Treatment" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="precisionTime" class="col-sm-4 control-label">Start Interval/precision Time</label>
                        <div class="col-sm-6">
                            <input type="time" id="precisionTime" value="${prescriptionToEdit.precisionTime}"
                                   name="precisionTime" placeholder="Time" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="intervalInHours" class="col-sm-4 control-label">Interval In Hours</label>
                        <div class="col-sm-6">
                            <input type="number" id="intervalInHours" value="${prescriptionToEdit.intervalInHours}"
                                   name="intervalInHours" placeholder="Interval In Hours" class="form-control">
                        </div>
                    </div>
                    <div class="col-sm-offset-2"><p style="color: #285e8e; font-weight: 700">DAYS:</p></div>
                    <div class="form-group">
                        <label for="sunday" class="col-sm-4 control-label">Sunday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.sunday == true}">
                                    <input type="checkbox" id="sunday" checked name="sunday" placeholder="Sunday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="sunday" name="sunday" placeholder="Sunday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="monday" class="col-sm-4 control-label">Monday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.monday == true}">
                                    <input type="checkbox" id="monday" checked name="monday" placeholder="Monday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="monday" name="monday" placeholder="Monday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tuesday" class="col-sm-4 control-label">Tuesday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.tuesday == true}">
                                    <input type="checkbox" id="tuesday" checked name="tuesday" placeholder="Tuesday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="tuesday" name="tuesday" placeholder="Tuesday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="wednesday" class="col-sm-4 control-label">Wednesday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.wednesday == true}">
                                    <input type="checkbox" id="wednesday" checked name="wednesday"
                                           placeholder="Wednesday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="wednesday" name="wednesday" placeholder="Wednesday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="thursday" class="col-sm-4 control-label">Thursday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.thursday == true}">
                                    <input type="checkbox" id="thursday" checked name="thursday" placeholder="Thursday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="thursday" name="thursday" placeholder="Thursday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="friday" class="col-sm-4 control-label">Friday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.friday == true}">
                                    <input type="checkbox" id="friday" checked name="friday" placeholder="Friday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="friday" name="friday" placeholder="Friday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="saturday" class="col-sm-4 control-label">Saturday</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.saturday == true}">
                                    <input type="checkbox" id="saturday" checked name="saturday" placeholder="Saturday">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="saturday" name="saturday" placeholder="Saturday">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <br>
                    <div class="col-sm-offset-2"><p style="color: #285e8e; font-weight: 700">ADDITIONAL:</p></div>
                    <div class="form-group">
                        <label for="beforeMeals" class="col-sm-4 control-label">Before Meals</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.beforeMeals == true}">
                                    <input type="checkbox" id="beforeMeals" checked name="beforeMeals"
                                           placeholder="Before Meals">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="beforeMeals" name="beforeMeals"
                                           placeholder="Before Meals">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="atMeals" class="col-sm-4 control-label">At Meals</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.atMeals == true}">
                                    <input type="checkbox" id="atMeals" checked name="atMeals" placeholder="At Meals">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="atMeals" name="atMeals" placeholder="At Meals">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="afterMeals" class="col-sm-4 control-label">After Meals</label>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${prescriptionToEdit.afterMeals == true}">
                                    <input type="checkbox" id="afterMeals" checked name="afterMeals"
                                           placeholder="After Meals">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="afterMeals" name="afterMeals" placeholder="After Meals">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div style="padding-left: 50%">
                        <input type="submit" class="btn login_btn" value="Edit"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form:form>
            </div>
        </div>
        <!-- *******MAIN CONTAINER******* -->
    </div>
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
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <img src="${pageContext.request.contextPath}/resources/images/doctor-avt.png" alt="">
                <p style="font-size: 20px; ">Doctor</p>
            </center>
            <li class="item" id="#patients">
                <a href="${pageContext.request.contextPath}/doctor/start-page" class="menu-btn"
                   style="font-size: 20px;">
                    <i class="fas fa-clinic-medical"></i><span>Main page</span>
                </a>
            </li>
            <li class="item" id="show-med-record" style="font-size: 20px;">
                <a href="${pageContext.request.contextPath}/doctor/medical-record/${medicalRecordToEdit.medicalRecordId}"
                   class="menu-btn">
                    <i class="fas fa-file-medical-alt"></i><span>Medical Record</span>
                </a>
            </li>
            <li class="item" id="add-prescription" style="font-size: 20px;">
                <a href="#" class="menu-btn">
                    <i class="fas fa-tablets"></i><span>Add prescription</span>
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
                <form action="${pageContext.request.contextPath}/doctor/add-prescription" method="post"
                      class="form-horizontal"
                      role="form">
                    <div style="padding-left: 20%">
                        <h2>Add Prescription</h2>
                        <span class="help-block">*Required fields</span>
                    </div>
                    <div class="form-group">
                        <label for="medicineProcedureName" class="col-sm-4 control-label">Name*</label>
                        <div class="col-sm-6">
                            <input type="text" id="medicineProcedureName"
                                   name="medicineAndProcedure.medicineProcedureName"
                                   placeholder="Name" class="form-control" autofocus>
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
                            <input type="text" id="dose" name="dose" placeholder="Dose" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="administeringMedicationMethod" class="col-sm-4 control-label">Administering
                            Medication Method</label>
                        <div class="col-sm-6">
                            <input type="text" id="administeringMedicationMethod" name="administeringMedicationMethod"
                                   placeholder="Administering Medication Method" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startTreatment" class="col-sm-4 control-label">Start Treatment*</label>
                        <div class="col-sm-6">
                            <input type="date" id="startTreatment" name="startTreatment"
                                   placeholder="Start Treatment" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="endTreatment" class="col-sm-4 control-label">End Treatment*</label>
                        <div class="col-sm-6">
                            <input type="date" id="endTreatment" name="endTreatment"
                                   placeholder="End Treatment" class="form-control">
                        </div>
                    </div>
                    <input type="hidden" id="patient"
                           name="patient.patientId" value="${patientId}"/>
                    <div class="form-group">
                        <label for="countPerDay" class="col-sm-4 control-label">Count Per Day</label>
                        <div class="col-sm-6">
                            <input type="number" id="countPerDay" name="treatmentTimePattern.countPerDay"
                                   placeholder="Count Per Day" class="form-control"
                                   value="0" min="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="precisionTime" class="col-sm-4 control-label">Time</label>
                        <div class="col-sm-6">
                            <input type="time" id="precisionTime" name="treatmentTimePattern.precisionTime"
                                   placeholder="Count Per Day" class="form-control">
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
                        <input type="submit" class="btn login_btn" value="Add prescription"
                               style="background-color: orange; opacity: 0.9;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- *******MAIN CONTAINER******* -->
    </div>
    <!--wrapper end-->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".sidebar-btn").click(function () {
            $(".wrapper").toggleClass("collapse");
        });
    });
</script>

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


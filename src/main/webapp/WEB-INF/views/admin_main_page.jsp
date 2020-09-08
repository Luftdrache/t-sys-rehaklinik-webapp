<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <!-- sidebar style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/doc_style.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <!-- popup about style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/about_popup_style.css">

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css"/>


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
                    <a href="${pageContext.request.contextPath}/admin/start-page" class="menu-btn">
                        <i class="fas fa-users"></i><span>Employees</span>
                    </a>
                </li>
                <li class="item" id="prescriptions">
                    <a href="${pageContext.request.contextPath}/admin/add-employee" class="menu-btn">
                        <i class="fas fa-user-plus"></i><span>Add new</span>
                    </a>
                </li>
                <li class="item" id="settings">
                    <a href="#settings" class="menu-btn">
                        <i class="fas fa-cog"></i><span>Settings <i class="fas fa-chevron-down drop-down"></i></span>
                    </a>
                    <div class="sub-menu">
                        <a href="#"><i class="fas fa-lock"></i><span>Password</span></a>
                        <a href="#"><i class="fas fa-language"></i><span>Language</span></a>
                    </div>
                </li>
                <li class="item">
                    <a href="#" class="menu-btn" onclick="showInfo()">
                        <i class="fas fa-info-circle"></i><span>About</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="min-height: 90vh; margin-bottom: 0; height: auto">
        <h5>ALL EMPLOYEES</h5>
        <div>
            <form:form class="form-inline mr-auto"
                       action="${pageContext.request.contextPath}/admin/find-employee-by-surname"
                       method="get">
                <input required class="form-control" type="text"
                       placeholder="Enter Surname" aria-label="Search"
                       name="surname"
                       oninvalid="this.setCustomValidity('The field is empty')"
                       oninput="setCustomValidity('')"
                       value="${surname}">
                <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                        style="background-color: orange"
                        type="submit"><i class="fas fa-search"></i> Search
                </button>
            </form:form>
        </div>
        <br>
        <div>
            <div class="card" style=" padding-left: 5px;padding-right: 5px; padding-bottom: 5px; padding-top: 0">
                <table class="table table-striped table-borderless .table-condensed" id="datatable">
                    <thead style="background: #A4D349">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Position</th>
                        <th scope="col">Category</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Role</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="table table-hover">
                    <c:forEach items="${allEmployees}" var="empl" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${empl.name}</td>
                            <td>${empl.position}</td>
                            <td>${empl.qualificationCategory.toString()}</td>
                            <td>${empl.phone}</td>
                            <td>${empl.email}</td>
                            <td>${empl.role}</td>
                            <td class="text-right row padding-right: 5px">
                                <form:form
                                        action="${pageContext.request.contextPath}/admin/employee-details/${empl.employeeId}"
                                        method="get">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Profile"
                                            style="background-color: yellowgreen; margin-left: 10px">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </form:form>
                                <form:form action="${pageContext.request.contextPath}/admin/edit/${empl.employeeId}"
                                           method="get">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                            style="background-color: yellowgreen; margin-left: 10px">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form:form>
                                <form:form action="${pageContext.request.contextPath}/admin/delete-employee"
                                           method="post">
                                    <input type="hidden" id="employeeIdToDelete" name="employeeIdToDelete"
                                           value="${empl.employeeId}">
                                    <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                            style="background-color: yellowgreen; margin-left: 10px">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${message}</p>
        </div>
    </div>
    <%@include file="shared/about_info_popup.jsp" %>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->
<%@include file="shared/js_scripts.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/about_info_popup.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</body>
</html>

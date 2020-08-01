<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <center class="profile">
                <img src="${pageContext.request.contextPath}/resources/images/admin-avt.png" alt="">
                <p>Admin</p>
            </center>
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
                    <a href="#" class="menu-btn">
                        <i class="fas fa-info-circle"></i><span>About</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--sidebar end-->
    <!-- *******MAIN CONTAINER******* -->
    <div class="main-container" style="height: 90vh;">
        Employees:
        <table class="table table-striped table-borderless .table-condensed ">
            <thead class="thead-mine">
            <tr class="tr-mine">
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
            <c:forEach items="${allEmployees}" var="empl">
                <tr>
                    <td>${empl.employeeId}</td>
                    <td>${empl.name}</td>
                    <td>${empl.position}</td>
                    <td>${empl.qualificationCategory}</td>
                    <td>${empl.phone}</td>
                    <td>${empl.email}</td>
                    <td>${empl.role}</td>
                    <td class="text-right row padding-right: 5px">
                        <form action="${pageContext.request.contextPath}/admin/employee-details/${empl.employeeId}"
                              method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Profile"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-eye"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/edit/${empl.employeeId}"
                              method="get">
                            <button type="submit" class="btn btn-primary btn-sm" value="Edit"
                                    style="background-color: yellowgreen">
                                <i class="fas fa-edit"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/delete-employee" method="post">
                            <input type="hidden" name="employeeIdToDelete" value="${empl.employeeId}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Delete"
                                    style="background-color: yellowgreen">
                                <i class="fa fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div content="container" class="col-sm-8 col-sm-offset-4">
            <p>${messageAboutEmployees}</p>
        </div>
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark blue lighten-2 mb-4">
                <div class="col-sm-5 col-sm-offset-6" id="navbarSupportedContent">
                    <form class="form-inline mr-auto"
                          action="${pageContext.request.contextPath}/admin/find-employee-by-surname"
                          method="get">
                        <input class="form-control" type="text" placeholder="Search" aria-label="Search" name="surname"
                               value="${surname}">
                        <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
                                style="background-color: orange"
                                type="submit">Search
                        </button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
    <!-- *******MAIN CONTAINER******* -->
</div>
<!--wrapper end-->

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

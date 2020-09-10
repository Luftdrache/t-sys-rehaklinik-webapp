<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png"
          type="image/png">

    <style>
        p {
            margin: 0;
            padding: 0;
        }

        .error {
            color: indianred;
            align-content: center;
            font-weight: 500
        }
    </style>
    <title>MedHelper</title>
</head>
<body>
<div class="container-fluid">
    <h1>MedHelper</h1>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-1" style="background-color: orange"></div>
            <div class="col-md-4" style="background-color: yellowgreen">
                <p class="font-italic"> Healing Hands. Caring Hearts. </p>
            </div>
        </div>
    </div>
    <div>
        <img src="${pageContext.request.contextPath}/resources/images/startimg.jpg"
             style="width: 100%; height: 80%; position: relative" class="shadow-lg" alt="Error image loading">
    </div>
    <div class="error" style="left: 43%; top: 34%; width: 20%; position: absolute;">
        <c:if test="${param.error != null}">
            <i id="error-message">You entered invalid <br> username or password</i>
        </c:if>
    </div>
    <div style="left: 41%; top: 44%; width: 22%; position: absolute;">
        <form:form action="/login/process" method="post">
            <div class="input-group form-group">
                <label>
                    <input type="text" required="" name="username" id="username" class="form-control"
                           placeholder="username"
                           oninvalid="this.setCustomValidity('Please enter a username')"
                           oninput="setCustomValidity('')"
                           style="background-color: whitesmoke">
                </label>
            </div>
            <div class="input-group form-group">
                <label>
                    <input type="password" required="" name="password" id="password" class="form-control"
                           placeholder="password"
                           oninvalid="this.setCustomValidity('Please enter a password')"
                           oninput="setCustomValidity('')"
                           style=" background-color: lightsteelblue">
                </label>
            </div>
            <div class="form-group" style="position: center">
                <input id="sign-in-button" type="submit" value="Sign in" class="btn login_btn"
                       style="background-color: orange; margin-left: 24%; opacity: 0.9">
            </div>
        </form:form>
    </div>
    <footer>
        © 2020 MedHelper. Professional hospital management system.
    </footer>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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


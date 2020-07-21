<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon_med_helper.png" type="image/png">

    <style>
        p {
            margin: 0;
            padding: 0;
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
    <div style="left: 40%; top: 42%; width: 19%; position: absolute;">
        <form>
            <div class="input-group form-group">
                <label>
                    <input type="text" class="form-control" placeholder="username" style="background-color: whitesmoke">
                </label>
            </div>
            <div class="input-group form-group">
                <label>
                    <input type="password" class="form-control" placeholder="password"
                           style="background-color: lightsteelblue">
                </label>
            </div>
            <div class="form-group" style="position: center">
                <input type="submit" value="Sign in" class="btn login_btn"
                       style="background-color: orange; margin-left: 32%; opacity: 0.9">
            </div>
        </form>
    </div>
    <div class=container">
        © 2020 Julia Dalskaya
    </div>
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


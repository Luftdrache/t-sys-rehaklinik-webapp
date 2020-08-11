<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/forbidden_page_style.css">
    <title>MedHelper</title>
</head>
<body>
<div class="container-fluid">
    <br><br>
    <div class="text-center">
        <div class="text-center">
            <img src="/resources/images/error_403.png" class="mx-auto d-block" alt="">
        </div>
        <br>
        <div class="forbidden">
            Sorry but you don’t have permission to access this page.
            <br>
            <button type="button" class="btn btn-danger" onclick="javascript:history.go(-1)">GET BACK</button>
        </div>
    </div>
</div>
</body>
</html>

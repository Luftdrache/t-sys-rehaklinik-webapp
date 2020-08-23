<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/forbidden_page_style.css">
    <title>MedHelper</title>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/resources/images/cat-exception-pic.jpg');
        background-size: 100% auto; background-position: top; background-repeat: no-repeat;">
<div class="container-fluid" style="padding: 0;  margin-right: 0px">
    <div class="exceptionMessage" style="padding-top: 30px; padding-left: 20px">
        <h5>${message}</h5>
    </div>
    <div style="padding-left: 50px">
        <h5>${message_part2}</h5>
    </div>
    <div style="padding-left: 90px;">
        <button type="button" class="btn btn-warning"
                onclick="javascript:history.go(-1)">GET BACK
        </button>
    </div>
</div>
</div>
</body>
</html>

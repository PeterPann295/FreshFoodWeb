<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <%@ include file="layouts/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="assets/css/dangNhap.css">
    <script src="assets/javascript/facebookAPI.js"></script>
</head>
<body>
<%@ include file="layouts/header.jsp" %>

<div class="container_form content">
    <form action="customer?action=forgetPassword" id="form-login"  method="post" class="form-container">
        <h1 class="text-center mb-3 text-success ">
            Quên Mật Khẩu <a href="#" class="logo-link"> <img
                style="width: 60px; height: 60px" src="assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>
        <div class="text-danger mb-3" style="text-align: center"> Vui lòng kiểm tra email </div>
        <div class="form-group mb-2">
            <label class="margin-label" for="username">Tên đăng nhập:</label> <input
                type="text" class="form-control" id="username" name="username"
                placeholder="Nhập tên đăng nhập" required>
            <div id="error-username" style="color: red"></div>
        </div>
        <div id="errorCaptcha" style="color: red"></div>
        <button type="submit"  style="height: 47.33px" class="btn btn-primary mb-2 margin-button">Quên Mật Khẩu
        </button>
    </form>
</div>

</body>
<footer>
    <%@ include file="layouts/footer.jsp" %>
</footer>
</html>
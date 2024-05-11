<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng kí tài khoản</title>
    <%@ include file="layouts/common.jsp"%>
    <script src="assets/javascript/dangKi.js"></script>
    <link rel="stylesheet" type="text/css" href="assets/css/dangKi.css">
</head>
<body>
<%@ include file="layouts/header.jsp"%>

<div class="container_form">
    <form class="form-container" action="DangKiTaiKhoan" method="post">
        <h1 class="text-center text-success">
            Đăng ký tài khoản <a href="#" class="logo-link"> <img
                style="width: 80px; height: 80px" src="assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>
        <div class="mb-3">
            <label for="username" class="form-label">Tên đăng nhập</label> <input
                type="text" class="form-control" id="username" name="username"
                placeholder="Nhập tên đăng nhập" value="${username}"
                required="required">
            <div class="text-danger">${requestScope.err_username}</div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu</label>
            <div class="input-group">
                <input type="password" class="form-control" id="password"
                       name="password" placeholder="Nhập mật khẩu"
                       aria-describedby="button-addon2" value="${password}"
                       required="required">
                <button class="btn btn-outline-secondary border border-start-0"
                        type="button" id="button-addon" onclick="hienThiMatKhau()">
                    <img class="size" id="hienThi" alt="" src="assets/images/hidden.png">
                </button>
            </div>
            <div class="text-danger">${err_password}</div>

        </div>
        <div class="mb-3">
            <label for="confirm-password" class="form-label">Nhập lại
                mật khẩu</label>
            <div class="input-group">
                <input type="password" class="form-control" id="confirm-password"
                       name="confirm-password" placeholder="Nhập mật khẩu"
                       aria-describedby="button-addon2" required="required">
                <button class="btn btn-outline-secondary border border-start-0"
                        type="button" id="button-addon2" onclick="hienThiMatKhau()">
                    <img class="size" id="hienThi-confirm" alt="" src="assets/images/hidden.png">
                </button>

            </div>
        </div>
        <div class="mb-3">
            <label for="full-name" class="form-label">Họ và tên</label> <input
                type="text" class="form-control" id="full-name" name="full-name"
                placeholder="Nhập họ và tên" value="${fullName}"
                required="required">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label> <input
                type="tel" class="form-control" id="phone" name="phone"
                placeholder="Nhập số điện thoại" value="${phone}"
                required="required">
            <div class="text-danger">${requestScope.err_phone}</div>

        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label> <input
                type="email" class="form-control" id="email" name="email"
                placeholder="Nhập email" value="${email}" required="required">
            <div class="text-danger">${requestScope.err_email}</div>

        </div>
        <button type="submit" class="btn btn-primary" style="width: 100%;">Đăng
            ký</button>
    </form>
</div>

<%@ include file="layouts/footer.jsp"%>
</body>
</html>
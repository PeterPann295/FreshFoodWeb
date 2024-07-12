<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <%@ include file="../layouts/common.jsp"%>
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../assets/css/dangKi.css">
</head>
<body>
<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="container_form">
                <form id="form_register" class="form-container" action="../admin?action=addAdmin" method="post">
                    <h1 class="text-center text-success">
                        Thêm Người Dùng Admin <a href="#" class="logo-link"> <img
                            style="width: 80px; height: 80px" src="../assets/images/rau.png" alt="Logo"
                            class="logo-image">
                    </a>
                    </h1>
                    <div class="mb-3">
                        <label for="username" class="form-label">Tên đăng nhập</label> <input
                            type="text" class="form-control" id="username" name="username"
                            placeholder="Nhập tên đăng nhập" value="${username}"
                            required="required">
                        <div class="err_username text-danger">${err_username}</div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="password"
                                   name="password" placeholder="Nhập mật khẩu"
                                   aria-describedby="button-addon2" value="${password}"
                                   required="required">

                        </div>
                        <div class="text-danger err_password">${err_password}</div>

                    </div>
                    <div class="mb-3">
                        <label for="confirm-password" class="form-label">Nhập lại
                            mật khẩu</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="confirm-password"
                                   name="confirm-password" placeholder="Nhập mật khẩu"
                                   aria-describedby="button-addon2" required="required">


                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="full-name" class="form-label">Họ và tên</label> <input
                            type="text" class="form-control" id="full-name" name="full-name"
                            placeholder="Nhập họ và tên" value="${fullName}"
                            required="required">
                        <div class="text-danger err_fullName">${err_fullName}</div>

                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Số điện thoại</label> <input
                            type="tel" class="form-control" id="phone" name="phone"
                            placeholder="Nhập số điện thoại" value="${phone}"
                            required="required">
                        <div class="text-danger err_numberPhone">${err_phone}</div>

                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label> <input
                            type="email" class="form-control" id="email" name="email"
                            placeholder="Nhập email" value="${email}" required="required">
                        <div class="text-danger err_email">${err_email}</div>

                    </div>
                    <button type="button" id="btn-submit" class="btn btn-primary" style="width: 100%;">Đăng
                        ký</button>
                </form>
            </div>

        </main>
    </div>


</div>
<script>
    let form_register = document.getElementById("form_register")
    let btn_submit = document.getElementById("btn-submit")

    btn_submit.onclick = () => {
        console.log("da vao day")
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let re_password = document.getElementById("confirm-password").value;
        let fullName = document.getElementById("full-name").value;
        let numberPhone = document.getElementById("phone").value;
        let email = document.getElementById("email").value;
        if(username.length < 8) {
            err_username = document.getElementsByClassName("err_username")[0];
            err_username.innerHTML= "<span> Tên tài khoản phải ít nhất 8 kí tự </span>"
            error = true
            return;
        }else {
            err_username = document.getElementsByClassName("err_username")[0];
            err_username.innerHTML= "<span>  </span>"
            error = false;
        }
        var uppercaseRegex = /[A-Z]/;
        var specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
        var hasUppercase = uppercaseRegex.test(password);
        var hasSpecialChar = specialCharRegex.test(password);
        var isValidLength = password.length >= 8;
        if (!isValidLength) {

            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span> Mật khẩu phải có ít nhất 8 kí tự</span>"
            error = true;
            return;
        }else  if (!hasSpecialChar) {
            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span> Mật khẩu phải có ít nhất 1 kí tự đặc biệt </span>"
            error = true;
            return;
        } else  if (!hasUppercase) {
            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span> Mật khẩu phải có ít nhất 1 kí tự in hoa </span>"
            error = true;
            return;
        } else {
            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span></span>"
            error = false;
        }

        if(fullName.length < 3){
            err_name = document.getElementsByClassName("err_fullName")[0];
            err_name.innerHTML= "<span> Tên không hợp lệ </span>"
            error = true;
            return;
        }else {
            err_name = document.getElementsByClassName("err_fullName")[0];
            err_name.innerHTML= "<span></span>"
            error = false;
        }
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var isValidEmail = emailRegex.test(email);
        if (!isValidEmail) {
            err_email = document.getElementsByClassName("err_email")[0];
            err_email.innerHTML= "<span> Email không hợp lệ </span>"
            error = true;
            return;
        }else {
            err_email = document.getElementsByClassName("err_email")[0];
            err_email.innerHTML= "<span></span>"
            error = false;
        }


        var phoneRegex = /^\d{10,}$/; // Biểu thức chính quy để kiểm tra số điện thoại, ở đây tôi giả sử số điện thoại có ít nhất 10 chữ số
        var isValidPhone = phoneRegex.test(numberPhone);
        if (!isValidPhone) {
            err_phone = document.getElementsByClassName("err_numberPhone")[0];
            err_phone.innerHTML= "<span> Số điện thoại không hợp lệ </span>"
            error = true;
            return;
        }else {
            err_phone = document.getElementsByClassName("err_numberPhone")[0];
            err_phone.innerHTML= "<span></span>"
            error = false;
        }
        if(error == false){
            form_register.submit();
            console.log("da vao day")
        }else {
            console.log("da vao else")
            return;
        }
    }
</script>

</body>
</html>
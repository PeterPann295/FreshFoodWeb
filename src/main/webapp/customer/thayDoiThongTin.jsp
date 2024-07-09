<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng kí tài khoản</title>
    <%@ include file="../layouts/common.jsp"%>
    <script src="assets/javascript/dangKi.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/css/dangKi.css">
</head>
<body>
<%@ include file="../layouts/header.jsp"%>

<div class="container_form">
    <form id="form_register" class="form-container" action="../customer?action=changeInformation" method="post">
        <h1 class="text-center text-success">
            Thay Đổi Thông Tin  <a href="#" class="logo-link"> <img
                style="width: 80px; height: 80px" src="/assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>
        <div class="mb-3">
            <label for="full-name" class="form-label">Họ và tên</label> <input
                type="text" class="form-control" id="full-name" name="full-name"
                placeholder="Nhập họ và tên" value="${sessionScope.customer_login.fullName}"
                required="required">
            <div class="text-danger err_fullName">${requestScope.err_fullName}</div>

        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label> <input
                type="tel" class="form-control" id="phone" name="phone"
                placeholder="Nhập số điện thoại" value="${sessionScope.customer_login.numberPhone}"
                required="required">
            <div class="text-danger err_numberPhone">${requestScope.err_phone}</div>

        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label> <input
                type="email" class="form-control" id="email" name="email"
                placeholder="Nhập email" value="${sessionScope.customer_login.email}" required="required">
            <div class="text-danger err_email">${requestScope.err_email}</div>

        </div>
        <button type="button" id="btn-submit" class="btn btn-primary" style="width: 100%;"> Thay Đổi Thông Tin </button>
    </form>
</div>
<script>
    let form_register = document.getElementById("form_register")
    let btn_submit = document.getElementById("btn-submit")

    btn_submit.onclick = () => {
        console.log("da vao day")
        let fullName = document.getElementById("full-name").value;
        let numberPhone = document.getElementById("phone").value;
        let email = document.getElementById("email").value;


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
<%@ include file="../layouts/footer.jsp"%>
</body>
</html>
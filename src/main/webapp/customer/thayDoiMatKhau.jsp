<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thay đổi mật khẩu</title>
    <%@ include file="../layouts/common.jsp"%>
    <script src="javascript/script.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/css/dangKi.css">
</head>
<body>
<%@ include file="../layouts/header.jsp"%>

<div class="container_form">
    <form class="form-container" id="form_change_password" action="../customer?action=changePassword" method="post">
        <h1 class="text-center text-success">
            Thay đổi mật khẩu <a href="#" class="logo-link"> <img
                style="width: 80px; height: 80px" src="../assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>

        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu mới</label>
            <div class="input-group">
                <input type="password" class="form-control" id="password"
                       name="new-password" placeholder="Nhập mật khẩu mới"
                       aria-describedby="button-addon2"  required="required">

            </div>
            <div class="text-danger err_password">${err_password}</div>
        </div>

        <div class="mb-3">
            <label for="confirm-password" class="form-label">Nhập lại
                mật khẩu mới</label>
            <div class="input-group">
                <input type="password" class="form-control" id="confirm-password"
                       name="confirm-newPassword" placeholder="Nhập lại mật khẩu mới"
                       aria-describedby="button-addon2" required="required">


            </div>
            <div class="text-danger"> ${err_password} </div>
        </div>

        <button type="button" id="btn-submit" class="btn btn-primary" style="width: 100%;">Thay đổi mật khẩu</button>
    </form>
</div>
<script>
    let form_register = document.getElementById("form_change_password")
    let btn_submit = document.getElementById("btn-submit")

    btn_submit.onclick = () => {
        let password = document.getElementById("password").value;
        let re_password = document.getElementById("confirm-password").value;

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
        } else  if(password != re_password){
            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span> Mật khẩu không khớp </span>"
            error = true;
            return;
        }
        else {
            err_password = document.getElementsByClassName("err_password")[0];
            err_password.innerHTML= "<span></span>"
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
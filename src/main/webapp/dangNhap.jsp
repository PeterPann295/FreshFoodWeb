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
    <form action="customer?action=login" id="form-login"  method="post" class="form-container">
        <h1 class="text-center mb-3 text-success ">
            Đăng Nhập <a href="#" class="logo-link"> <img
                style="width: 60px; height: 60px" src="assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>
        <div class="text-danger mb-3" style="text-align: center">${error_login}</div>
        <div class="text-danger mb-3" style="text-align: center">${register_success}</div>
        <div class="form-group mb-2">
            <label class="margin-label" for="username">Tên đăng nhập:</label> <input
                type="text" class="form-control" id="username" name="username"
                placeholder="Nhập tên đăng nhập" required>
            <div id="error-username" style="color: red"></div>
        </div>
        <div class="form-group mb-2">
            <label class="margin-label" for="password">Mật khẩu:</label> <input
                type="password" class="form-control" id="password" name="password"
                placeholder="Nhập mật khẩu" required>
            <div id="error-password" style="color: red"></div>

        </div>
        <div class="g-recaptcha " style=""
             data-sitekey="6LeIwO8pAAAAAORhIO7KFhVarndvrRWaZSN_PUYm"
        > </div>
        <div id="errorCaptcha" style="color: red"></div>
              <button type="button" onclick="checkCaptcha()" style="height: 47.33px" class="btn btn-primary mb-2 margin-button">Đăng
            Nhập
        </button>
        <div>
            <div class="d-flex gap-3 flex-column w-100 mt-2 ">
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid
                &redirect_uri=http://localhost:8080/customer?action=loginGoogle&response_type=code&client_id=585555712223-r4afja7qo8tbh01titqa9m8qinn5k7bu.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-danger">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-google" viewBox="0 0 16 16">
                        <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                    </svg>
                    <span class="ms-2 fs-6">Đăng nhập bằng google</span>
                </a>
                <div onlogin="checkLoginState();" class="btn btn-lg btn-primary">
                    <div class="fb-login-button" scope="public_profile,email" onlogin="checkLoginState();" data-width=""
                         data-size="medium" data-button-type="login_with" data-layout="" data-auto-logout-link="false"
                         data-use-continue-as="false"></div>
                </div>
            </div>
        </div>
        <div class="mt-2">
            <p>
                Nếu bạn chưa có tài khoản, hãy <a class="primary-link"
                                                  href="dangKi.jsp"> đăng kí </a>
                , hoặc bạn <a class="primary-link"
                              href="quenMatKhau.jsp"> quên mật khẩu </a>
            </p>
        </div>
    </form>
</div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js"></script>

<script src="https://www.google.com/recaptcha/api.js"></script>
<script>
    function checkCaptcha() {
        var form_login = document.getElementById("form-login");
        var error = document.getElementById("errorCaptcha");
        var error_username = document.getElementById("error-username");
        var error_password = document.getElementById("error-password");

        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        if(grecaptcha.getResponse().length !== 0){
            if(username.length < 8){
                error_username.textContent = "Tên người dùng phải có ít nhất 8 ký tự.";
            }else if(password.length < 8){
                error_password.textContent = "Mật khẩu phải có ít nhất 8 ký tự.";
            }else{
                form_login.submit();
            }
        }else {
            error.textContent = "Vui lòng hãy xác nhận mình không phải là robot.";
        }
    }

</script>
</body>
<footer>
    <%@ include file="layouts/footer.jsp" %>
</footer>
</html>
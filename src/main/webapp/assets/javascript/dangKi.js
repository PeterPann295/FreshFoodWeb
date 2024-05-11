function hienThiMatKhau() {
    var password = document.getElementById("password");
    var hienThi = document.getElementById("hienThi");
    var password2 = document.getElementById("confirm-password");
    var hienThi2 = document.getElementById("hienThi-confirm");
    if(password.type =="password"){
        password.type="text";
        password2.type="text";
        hienThi2.src="assets/images/view.png";
        hienThi.src ="assets/images/view.png";
    }else {
        password.type="password";
        hienThi.src ="assets/images/hidden.png";
        password2.type="password";
        hienThi2.src="assets/images/hidden.png";
    }
}

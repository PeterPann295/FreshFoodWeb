<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng kí tài khoản</title>
    <%@ include file="/layouts/common.jsp"%>
    <script src="assets/javascript/dangKi.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/css/dangKi.css">
    <style>
        /* CSS cơ bản */
        .dropdown {
            margin: 10px 0;
        }
    </style>
</head>
<body>
<%@ include file="/layouts/header.jsp"%>
<div class="container_form">
    <form id="form_register" class="form-container" action="../customer?action=confirmAddress" method="post">
        <h1 class="text-center text-success">
            Thông Tin Giao Hàng <a href="#" class="logo-link"> <img
                style="width: 80px; height: 80px" src="/assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>
        <div class="mb-3">
            <label for="to_customer" class="form-label">Tên người nhận</label> <input
                type="text" class="form-control" id="to_customer" name="to_customer"
                placeholder="Nhập tên người nhận"
                required="required">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại người nhận</label> <input
                type="tel" class="form-control" id="phone" name="phone"
                placeholder="Nhập số điện thoại người nhận" value="${phone}"
                required="required">
            <div class="text-danger err_numberPhone">${requestScope.err_phone}</div>

        </div>
        <div class="mb-3">
            <div class="dropdown">
                <label for="province" class="form-label">Tỉnh / Thành Phố:</label>
                <select id="province" class="form-control" name="provinceId">
                    <option value="">Chọn Tỉnh / Thành Phố  </option>
                </select>
            </div>
        </div>
        <div class="dropdown">
            <label for="district" class="form-label">Quận/Huyện:</label>
            <select id="district" class="form-control" name="districtId">
                <option value="">Chọn Quận/Huyện</option>
            </select>
        </div>
        <div class="dropdown">
            <label for="ward" class="form-label" >Phường/Xã:</label>
            <select id="ward" class="form-control" name="wardId">
                <option value="">Chọn Phường/Xã</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="address_detail" class="form-label">Nhập Địa Chỉ Cụ Thể
                </label>
            <textarea type="text" class="form-control" id="address_detail"
                      name="address_detail" placeholder="Nhập địa chỉ cụ thể " rows="5"
                      cols="50"> </textarea>

        </div>
        <button type="submit" id="btn-submit" class="btn btn-primary" style="width: 100%;">Xác Nhận Địa Chỉ</button>
    </form>
</div>
<script src="javascript/fetchAPIAddress.js" ></script>
<%@ include file="/layouts/footer.jsp"%>
</body>
</html>
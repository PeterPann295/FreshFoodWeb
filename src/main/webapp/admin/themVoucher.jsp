<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>

<jsp:useBean id="voucherDAO" class="database.VoucherDao" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Voucher</title>
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
                <form class="form-container" action="../admin?action=addVoucher" method="post">

                    <h1 class="text-center">Thêm Voucher</h1>
                    <div class="mb-3">
                        <label for="code" class="form-label">Mã Voucher</label>
                        <input type="text" class="form-control" id="code"
                               name="code" placeholder="Nhập mã voucher" value="${code}">
                    </div>

                    <div class="mb-3">
                        <label for="discount" class="form-label">Giảm Giá</label>
                        <input type="number" class="form-control" id="discount" name="discount"
                               placeholder="Nhập giá trị giảm giá" value="${discount}">
                        <div class="text-danger">${err_discount}</div>
                    </div>



                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary width-btn">Thêm Voucher</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>

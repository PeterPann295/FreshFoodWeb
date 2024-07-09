<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="layouts/svg.jsp"%>

<jsp:useBean id="discountDAO" class="database.DiscountDao" />
<jsp:useBean id="categoryDAO" class="database.CategoryDao" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật voucher</title>
    <%@ include file="../layouts/common.jsp"%>
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../assets/css/dangKi.css">
</head>
<body>
<%@ include file="../layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="./layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="container_form">
                <form class="form-container" action="../admin?action=updateVoucher&voucherId=${voucher.id}" method="post">
                    <h1 class="text-center">Cập nhật voucher </h1>

                    <div class="mb-3">
                        <label for="code" class="form-label">Mã voucher</label>
                        <input type="text" class="form-control" id="code" name="code" placeholder="Nhập mã voucher" value="${voucher.code}">
                    </div>

                    <div class="mb-3">
                        <label for="discount" class="form-label">Giảm giá</label>
                        <input type="number" class="form-control" id="discount" name="discount" placeholder="Nhập giảm giá" value="${voucher.discount}">
                    </div>

                    <button type="submit" class="btn btn-primary">Cập nhật voucher</button>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>

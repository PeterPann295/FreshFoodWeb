<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>

<jsp:useBean id="discountDAO" class="database.DiscountDao" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Discount</title>
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
                <form class="form-container" action="../admin?action=addDiscount" method="post">

                    <h1 class="text-center">Thêm Discount</h1>
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên Discount</label>
                        <input type="text" class="form-control" id="name"
                               name="name" placeholder="Nhập tên discount" value="${name}">
                    </div>

                    <div class="mb-3">
                        <label for="percent" class="form-label">Phần trăm giảm</label>
                        <input type="number" class="form-control" id="percent" name="percent"
                               placeholder="Nhập phần trăm giảm" value="${percent}">
                        <div class="text-danger">${err_percent}</div>
                    </div>

                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary width-btn">Thêm Discount</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>

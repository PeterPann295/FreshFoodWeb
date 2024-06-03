<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>

<jsp:useBean id="discountDAO" class="database.DiscountDao"
/>
<jsp:useBean id="categoryDAO" class="database.CategoryDao"
/>
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
        <form class="form-container" action="../admin?action=updateCustomer&customerId=${updateCustomer.id}" method="post"
              >

          <h2 class="text-center">Cập nhật thông tin khách hàng   </h2>

          <div class="mb-3">
            <label for="fullName" class="form-label">Tên Khách Hàng</label>
            <input type="text" class="form-control" id="fullName"
                   name="fullName" placeholder="Nhập tên sản phẩm" value="${updateCustomer.fullName}">
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email"
                   name="email" placeholder="Nhập tên sản phẩm" value="${updateCustomer.email}">
          </div>
          <div class="mb-3">
            <label for="numberPhone" class="form-label"> Số Điện Thoại </label>
            <input type="number" class="form-control" id="numberPhone"
                   name="numberPhone" placeholder="Nhập tên sản phẩm" value="${updateCustomer.numberPhone}">
          </div>
          <div class="mb-3">
            <button type="submit" class="btn btn-primary width-btn">Cập Nhật
              Khách Hàng </button>
          </div>
        </form>
      </div>
    </main>
  </div>
</div>
<c:remove var="updateCustomer" scope="session" />
</body>
</html>
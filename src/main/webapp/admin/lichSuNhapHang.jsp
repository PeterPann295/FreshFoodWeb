
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="importProduct" class="database.ImportProductDao" />


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sản phẩm</title>
  <style>
    td, th {
      vertical-align: middle; /* Căn giữa theo chiều dọc */
    }
  </style>
  <jsp:include page="layouts/cssDatatable.jsp" />

</head>
<body>

<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
  <div class="row">
    <%@ include file="layouts/navMenu.jsp"%>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

      <div
              class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h3 class="h2">Lịch sử nhập hàng</h3>
        <div class="btn-toolbar mb-2 mb-md-0">
          <a href="nhapSanPham.jsp">
            <button type="button" class="btn btn-sm btn-outline-success">
              Nhập sản phẩm </button>
          </a>
        </div>
      </div>
      <table class="table" id="tableHistoryImportProduct">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col"></th>
          <th scope="col">Tên Sản Phẩm</th>
          <th scope="col"> Số lượng </th>
          <th scope="col"> Ngày nhập </th>
          <th scope="col"> Người nhập </th>


        </tr>
        </thead>
        <tbody>

        <c:forEach var="p" items="${importProduct.selectAll()}">
          <tr>
            <td> ${p.id} </td>
            <td><img style="width: 60px; height: 50px"
                     src="${p.product.imageUrl}" alt="Logo" class="logo-image"></td>
            <td> ${p.product.name} </td>
            <td> ${p.quatity} </td>
            <td> <fmt:formatDate value="${p.date}" pattern="dd/MM/yyyy" />
            </td>
            <td> ${p.customer.username} </td>

          </tr>

        </c:forEach>

        </tbody>
      </table>

    </main>
  </div>
</div>
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
  new DataTable('#tableHistoryImportProduct', {
    layout: {
      topStart: {
        buttons: [
          {
            extend: 'copy',

          },
          {
            extend: 'csv',

          },
          {
            extend: 'excel',

          },
          {
            extend: 'pdf',

          },
          {
            extend: 'print',

          },

        ]
      }
    }
  });
</script>

</body>
</html>

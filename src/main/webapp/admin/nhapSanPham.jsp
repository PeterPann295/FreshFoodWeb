
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="productDAO" class="database.ProductDao" />
<jsp:useBean id="importProduct" class="database.ImportProductDao" />
<jsp:useBean id="orderDao" class="database.OrderDao"/>

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
                <h3 class="h2">Danh Sách Sản Phẩm</h3>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="lichSuNhapHang.jsp">
                        <button type="button" class="btn btn-sm btn-outline-success">
                            Lịch sử nhập hàng </button>
                    </a>
                </div>
            </div>
            <table class="table" id="tableImportProduct">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"></th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Giá Bán</th>
                    <th scope="col">Số lượng đã nhập</th>
                    <th scope="col">Số lượng đã bán</th>
                    <th scope="col">Tồn kho </th>
                    <th scope="col"></th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${productDAO.selectAll()}">
                    <tr>
                        <th scope="row">${p.id}</th>
                        <td><img style="width: 60px; height: 50px"
                                 src="${p.imageUrl}" alt="Logo" class="logo-image"></td>
                        <td>
                            <c:set var="totalInStock" value="${importProduct.selectToTalProductInStock(p.id)}" />

                            <c:choose>
                                <c:when test="${totalInStock <= 0}">
                                    <p class="text-danger">${p.name}</p>
                                </c:when>
                                <c:when test="${totalInStock <= 5}">
                                    <p class="text-warning">${p.name}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="text-success">${p.name}</p>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td><fmt:formatNumber value="${p.price}" type="currency"
                                              currencyCode="VND" minFractionDigits="0"  /></td>
                        <td class="text-center align-middle">${importProduct.selectTotalQuatityImportByProductId(p.id)}</td>
                        <td class="text-center align-middle">${orderDao.selectTotalProductSold(p.id)}</td>
                        <td class="text-center align-middle">${importProduct.selectToTalProductInStock(p.id)}</td>
                        <td>
                            <div class="modal fade" id="import-${p.id}" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalToggleLabel"> Nhập số lượng sản phẩm muốn nhập  </h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="../admin?action=importProduct" method="post">
                                        <div class="modal-body">
                                            <input type="hidden" name="productId" value="${p.id}">
                                            <h5 style="text-align: center"> Sản phẩm: ${p.name} <img style="width: 60px; height: 50px"
                                                                          src="${p.imageUrl}" alt="Logo" class="logo-image"> </h5>
                                            <label for="imgProduct" class="form-label">Số lượng: </label> <input type="number" class="form-control" id="imgProduct"
                                                                    name="quatity" placeholder="Nhập số lượng sản phẩm" min="1">
                                        </div>
                                        <div class="modal-footer">
                                                <button class="btn btn-primary" >Xác nhận nhập hàng</button>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-success" data-bs-target="#import-${p.id}" data-bs-toggle="modal"><i class="bi bi-plus-lg"></i></button>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </main>
    </div>
</div>
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
    new DataTable('#tableImportProduct', {
        layout: {
            topStart: {
                buttons: [
                    {
                        extend: 'copy',
                        exportOptions: {
                            columns: ':not(:eq(7))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'csv',
                        exportOptions: {
                            columns: ':not(:eq(7))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'excel',
                        exportOptions: {
                            columns: ':not(:eq(7))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'pdf',
                        exportOptions: {
                            columns: ':not(:eq(7))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'print',
                        exportOptions: {
                            columns: ':not(:eq(7))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },

                ]
            }
        },
        columnDefs: [
            { targets: 7, orderable: false } ,
        ]
    });
</script>


</body>
</html>

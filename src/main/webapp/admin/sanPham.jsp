
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
                <span style="color: red"> ${response} </span>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="themSanPham.jsp">
                        <button type="button" class="btn btn-sm btn-outline-success">
                        Thêm sản phẩm     </button>
                    </a>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"></th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Giá Bán</th>
                    <th scope="col">Danh Mục</th>
                    <th scope="col">CT Giảm Giá</th>
                    <th scope="col">Trình Trạng</th>
                    <th scope="col"></th>


                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${productDAO.selectAll()}">
                    <tr>
                        <th scope="row">${p.id}</th>
                        <td><img style="width: 60px; height: 50px"
                                 src="${p.imageUrl}" alt="Logo" class="logo-image"></td>
                        <td>${p.name}</td>
                        <td><fmt:formatNumber value="${p.price}" type="currency"
                                              currencyCode="VND" minFractionDigits="0"  /></td>
                        <td>${p.category.name}</td>
                        <td><c:choose>
                            <c:when test="${p.discount != null}">

                                ${p.discount.name }


                            </c:when>
                            <c:otherwise>
                                Không
                            </c:otherwise>
                        </c:choose></td>
                        <td><c:choose>
                            <c:when test="${importProduct.selectToTalProductInStock(p.id) > 0}">
                                Còn Hàng
                            </c:when>
                            <c:otherwise>
                                Hết Hàng
                            </c:otherwise>
                        </c:choose></td>
                        <td><a href="../admin?action=goUpdateProduct&productId=${p.id}"
                               style="text-decoration: none">
                            <button class="btn btn-success btn-sm">Chỉnh Sửa</button>
                        </a> <a href="../admin?action=deleteProduct&productId=${p.id}"
                                style="text-decoration: none">
                            <button class="btn btn-success btn-sm">Xóa SP</button>
                        </a>
                    </tr>

                </c:forEach>
                </tbody>
            </table>

        </main>
    </div>
</div>
<c:remove var="response" scope="session" />
</body>
</html>

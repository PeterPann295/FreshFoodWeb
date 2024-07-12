
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
                <h3 class="h2">Xu hướng bán hàng của sản phẩm</h3>
            </div>
            <table class="table" id="tableImportProduct">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"></th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Tháng 5</th>
                    <th scope="col">Tháng 6</th>
                    <th scope="col">Tháng 7</th>
                    <th scope="col">Xu Hướng </th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${productDAO.selectTrendProduct()}">
                    <tr>
                        <th scope="row">${p.product.id}</th>
                        <td><img style="width: 60px; height: 50px"
                                 src="${p.product.imageUrl}" alt="Logo" class="logo-image"></td>
                        <td>
                            <c:set var="totalInStock" value="${importProduct.selectToTalProductInStock(p.product.id)}" />

                            <c:choose>
                                <c:when test="${totalInStock <= 0}">
                                    <p class="text-danger">${p.product.name}</p>
                                </c:when>
                                <c:when test="${totalInStock <= 5}">
                                    <p class="text-warning">${p.product.name}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="text-success">${p.product.name}</p>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td class="text-center aligh-middle">${p.quantityFirstMonth}</td>
                        <td class="text-center align-middle">${p.quantitySecondMonth}</td>
                        <td class="text-center align-middle">${p.quantityThirdMonth}</td>
                        <td class="text-center align-middle">${p.trend}</td>
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
        },
    });
</script>


</body>
</html>

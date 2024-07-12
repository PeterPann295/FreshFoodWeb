
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
                <h3 class="h2">Xu Hướng Đặt Hàng</h3>
            </div>
            <table class="table" id="tableImportProduct">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên Khách Hàng</th>
                    <th scope="col">Tháng 5</th>
                    <th scope="col">Tháng 6</th>
                    <th scope="col">Tháng 7</th>
                    <th scope="col">Tổng Tiền</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${orderDao.selectOrderTrend()}">
                    <tr>
                        <th scope="row">${p.customer.id}</th>
                        <td>${p.customer.fullName}</td>


                        <td class="">x${p.orderQuantityTwoMonthAgo} : <fmt:formatNumber value="${p.totalTwoMonthAgo}" type="currency"
                                                                                                                currencyCode="VND" minFractionDigits="0"  /> </td>
                        <td class="">x${p.orderQuantityLastMonth} : <fmt:formatNumber value="${p.totalLastMonth}" type="currency"
                                                                                                              currencyCode="VND" minFractionDigits="0"  /> </td>
                        <td class="">x${p.orderQuantityThisMonth} : <fmt:formatNumber value="${p.totalThisMonth}" type="currency"
                                                                                                              currencyCode="VND" minFractionDigits="0"  /> </td>
                        <td class=""><fmt:formatNumber value="${p.totalAmount}" type="currency"
                                                                               currencyCode="VND" minFractionDigits="0"  /></td>
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

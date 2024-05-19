<%--
  Created by IntelliJ IDEA.
  User: lemin
  Date: 5/19/2024
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
    <link rel="stylesheet" type="text/css" href="/assets/css/dangKi.css">

</head>
<body>
<%@ include file="/layouts/header.jsp"%>

<div class="container_form">
    <form class="form-container" action="xacnhandathang" method="get">
        <table class="table">

            <tr>
                <th colspan="2" style="text-align: center;"> <h3 class="text-success">Xác Nhận Mua Hàng</h3> </th>
            </tr>
            <tr>
                <td>Tên Người Nhận Hàng </td>
                <td> ${order.toName} </td>
            </tr>
            <tr>
                <td> Số Điện Thoại </td>
                <td> ${order.numberPhone} </td>
            </tr>
            <tr>
                <td> Địa chỉ nhận hàng </td>
                <td> ${order.to_address} </td>
            </tr>
            <tr>
                <td> Sản Phẩm </td>
                <td> <c:forEach var="p" items="${order.orderItems}">
                    <p> <img
                            style="width: 40px; height: 40px" src="${p.product.imageUrl}" alt="Logo"
                    > <b>${p.product.name}</b>  : x${p.quantity} </p>
                </c:forEach>
                </td>
            </tr>
            <tr>
                <td> <h5> Tổng tiền sản phẩm </h5> </td>
                <td> <b><fmt:formatNumber value="${order.totalPriceProduct()}" type="currency"
                                          currencyCode="VND" /></b> </td>
            </tr>
            <tr>
                <td> <h5> Phí vận chuyển</h5> </td>
                <td> <b><fmt:formatNumber value="${order.deliveryFee}" type="currency"
                                          currencyCode="VND" /></b> </td>
            </tr>
            <tr>
                <td> <h5> Tổng tiền thanh toán </h5> </td>
                <td> <b><fmt:formatNumber value="${order.total}" type="currency"
                                          currencyCode="VND" /></b> </td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary" style="width: 100%;">Xác Nhận Đặt Hàng</button>
    </form>
</div>

<%@ include file="/layouts/footer.jsp"%>
</body>
</html>

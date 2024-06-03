<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="parentCategoryDAO" class="database.ParentCategoryDao" />
<jsp:useBean id="orderDao" class="database.OrderDao" />
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <link href="assets/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Danh Mục Lớn</title>
    <style>
        td, th {
            vertical-align: middle;
        }
        .a{
            text-decoration: none;
        }
        <%
Integer status = (Integer) session.getAttribute("status");
if (status == null) {
   status = 0;
}
%>
    </style>
</head>
<body>

<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div class="container">
                <ul class="nav nav-pills mb-3 mt-3" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="a" href="donHang.jsp"><button class="nav-link <%= (status == 0) ? "active" : "" %>" id="all-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="all-orders" aria-selected="false">Tất cả đơn hàng  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=1"><button class="nav-link <%= (status == 1) ? "active" : "" %>" id="pending-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="pending-orders" aria-selected="true"> Chờ xác nhận  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=2"> <button class="nav-link <%= (status == 2) ? "active" : "" %>" id="box-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="delivered-orders" aria-selected="false"> Chờ đóng gói  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=3"> <button class="nav-link <%= (status == 3) ? "active" : "" %>" id="delivery-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Chờ giao hàng </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=4"> <button class="nav-link <%= (status == 4) ? "active" : "" %>" id="delivering-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Đã giao </button> </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=5"><button class="nav-link <%= (status == 5) ? "active" : "" %>" id="cancelled-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Đã hủy </button></a>
                    </li>
                </ul>
                <h4 class="text">
                    <span> ${emtyOrder} </span>
                </h4>
                <div class="col-md-12">
                    <div class="card card-plain">

                        <div class="card-body">

                            <div class="table-responsive">

                                <table class="table table-shopping">
                                    <tr>

                                        <th class="text-center">Mã Đơn Hàng</th>
                                        <th class="text-center">Sản Phẩm</th>
                                        <th class="text-center">Ngày Đặt</th>
                                        <th class="text-center">Tình Trạng</th>
                                        <th class="text-center">Tổng Tiền</th>
                                        <th></th>

                                    </tr>

                                    <c:forEach items="${orderStatusList}" var="p">
                                        <tr>

                                            <td class="text-center align-middle"> <b> ${p.id} </b> </td>
                                            <td class="text-center"><c:forEach items="${p.orderItems}" var="c">

                                                <p>
                                                    <img style="width: 40px; height: 40px"
                                                         src="${c.product.imageUrl}" alt="Logo">
                                                    <b>${c.product.name}</b>
                                                </p>

                                            </c:forEach></td>
                                            <td class="text-center align-middle"><fmt:formatDate value="${p.date}" pattern="dd/MM/yyyy" /></td>
                                            <td class="text-center align-middle"> ${p.status.name} </td>
                                            <td class="text-center align-middle"><b><fmt:formatNumber value="${p.total}"
                                                                                                      type="currency" currencyCode="VND" minFractionDigits="0"/></b></td>
                                            <td class="text-center align-middle"> <a class="a" href="../admin?action=detailOrder&orderId=${p.id}"><button class="btn btn-success"> Chi tiết </button></a>
                                                <c:if test="${p.status.id == 3}">
                                                    <a class="a" href="../admin?action=updateOrder&status=4&orderId=${p.id}"><button class="btn btn-success"> Đã giao hàng </button></a>

                                                </c:if>
                                                <c:if test="${p.status.id == 1}">
                                                    <a class="a" href="../admin?action=updateOrder&status=2&orderId=${p.id}"><button class="btn btn-success"> Xác nhận </button></a>

                                                </c:if>
                                                <c:if test="${p.status.id == 2}">
                                                    <a class="a" href="../admin?action=updateOrder&status=3&orderId=${p.id}"><button class="btn btn-success"> Đóng gói </button></a>

                                                </c:if><c:if test="${p.status.id < 3}">
                                                    <a class="a" href="../admin?action=updateOrder&status=5&orderId=${p.id}"><button class="btn btn-success"> Hủy </button></a>
                                                </c:if>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </table>

                            </div>

                        </div>

                    </div>



                </div>
            </div>



        </main>
    </div>
</div>

</body>
</html>
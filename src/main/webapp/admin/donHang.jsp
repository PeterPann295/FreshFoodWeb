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
        .hd {
            display: none;
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

            <div class="container">
                <ul class="nav nav-pills mb-3 mt-3" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=0"><button class="nav-link active" id="all-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="all-orders" aria-selected="false">Tất cả đơn hàng  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=1"><button class="nav-link" id="pending-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="pending-orders" aria-selected="true"> Chờ xác nhận  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=2"> <button class="nav-link" id="box-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="delivered-orders" aria-selected="false"> Chờ đóng gói  </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=3"> <button class="nav-link" id="delivery-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Chờ giao hàng </button></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=4"> <button class="nav-link" id="delivering-orders" data-bs-toggle="pill"  type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Đã giao </button> </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="a" href="../admin?action=getOrderStatus&status=5"><button class="nav-link" id="cancelled-orders" data-bs-toggle="pill" type="button" role="tab" aria-controls="cancelled-orders" aria-selected="false"> Đã hủy </button></a>
                    </li>
                </ul>
                <h4 class="text">
                    <span> ${emtyOrder} </span>
                </h4>

            </div>

            <table id="tableOrder" class="table table-shopping" >
                <thead>

                    <th class="text-center">Mã Đơn Hàng</th>
                    <th class="text-center">Sản Phẩm</th>
                    <th class="text-center">Ngày Đặt</th>
                    <th class="text-center">Tình Trạng</th>
                    <th class="text-center">Tổng Tiền</th>
                    <th></th>
                    <th class="hd"> Người đặt hàng (id) </th>
                    <th class="hd"> Tên người đặt </th>
                    <th class="hd"> Tên người nhận </th>
                    <th class="hd"> Số điện thoại người nhận </th>
                    <th class="hd"> Địa chỉ người nhận </th>
                    <th class="hd"> Ngày giao hàng dự kiến </th>
                </thead>

                <c:forEach items="${orderDao.selectAll()}" var="p">
                    <tr>

                        <td class="text-center align-middle"> <b> ${p.id} </b> </td>
                        <td class="text-center"><c:forEach items="${p.orderItems}" var="c">

                            <p>
                                <img style="width: 40px; height: 40px"
                                     src="${c.product.imageUrl}" alt="Logo">
                                <b>${c.product.name} </b> x${c.quantity}
                            </p>

                        </c:forEach></td>
                        <td class="text-center align-middle"><fmt:formatDate value="${p.date}" pattern="dd/MM/yyyy" /></td>
                        <td class="text-center align-middle"> ${p.status.name} </td>
                        <td class="text-center align-middle"><b><fmt:formatNumber value="${p.total}"
                                                                                  type="currency" currencyCode="VND" minFractionDigits="0"  /></b></td>
                        <td class="text-center align-middle"> <a class="a" href="../admin?action=detailOrder&orderId=${p.id}"><button class="btn btn-success"> Chi tiết </button></a>
                            <c:if test="${p.status.id == 3}">
                                <a class="a" href="../admin?action=updateOrder&status=4&orderId=${p.id}"><button class="btn btn-success">Đã Giao hàng </button></a>

                            </c:if>
                            <c:if test="${p.status.id == 1}">
                                <a class="a" href="../admin?action=updateOrder&status=2&orderId=${p.id}"><button class="btn btn-success"> Xác nhận  </button></a>

                            </c:if>
                            <c:if test="${p.status.id == 2}">
                                <a class="a" href="../admin?action=updateOrder&status=3&orderId=${p.id}"><button class="btn btn-success"> Đóng gói  </button></a>

                            </c:if><c:if test="${p.status.id < 3}">
                                <a class="a" href="../admin?action=updateOrder&status=5&orderId=${p.id}"><button class="btn btn-success"> Hủy </button></a>
                            </c:if>
                        </td>
                        <td class="hd"> ${p.customer.id} </td>
                        <td class="hd"> ${p.customer.fullName} </td>
                        <td class="hd"> ${p.toName} </td>
                        <td class="hd"> ${p.numberPhone} </td>
                        <td class="hd"> ${p.to_address} </td>
                        <td class="hd"> <fmt:formatDate value="${p.deliveryDate}" pattern="dd/MM/yyyy" /> </td>

                    </tr>

                </c:forEach>
            </table>


        </main>
    </div>
</div>
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
    new DataTable('#tableOrder', {
        layout: {
            topStart: {
                buttons: [
                    {
                        extend: 'copy',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'csv',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'excel',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'pdf',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'print',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },

                ]
            }
        },
        columnDefs: [
            { targets: 5, orderable: false } ,
            { "targets": [6, 7, 8, 9, 10, 11], "visible": false } // Chỉ số của các cột bạn muốn ẩn (0-based)
// Xét ordering cho cột thứ 6 (số thứ tự là 5)
        ]
    });
</script>

</body>
</html>
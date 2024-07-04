<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="voucherDAO" class="database.VoucherDao" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Voucher</title>
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
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h2">Danh Sách Voucher</h3>
                <span style="color: red"> ${response} </span>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="themVoucher.jsp">
                        <button type="button" class="btn btn-sm btn-outline-success">Thêm Voucher</button>
                    </a>
                </div>
            </div>
            <table class="table" id="tableVoucher">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên Voucher</th>
                    <th scope="col">Mã Voucher</th>
                    <th scope="col">Giảm Giá</th>
                    <th scope="col">Ngày Hết Hạn</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="v" items="${voucherDAO.selectAll()}">
                    <tr>
                        <th scope="row">${v.id}</th>
                        <td>${v.name}</td>
                        <td>${v.code}</td>
                        <td><fmt:formatNumber value="${v.discount}" type="currency" currencyCode="VND" minFractionDigits="0" /></td>
                        <td>${v.expiryDate}</td>
                        <td>
                            <a href="../admin?action=goUpdateVoucher&voucherId=${v.id}" style="text-decoration: none">
                                <button class="btn btn-success btn-sm"><i class="bi bi-pencil-square"> </i></button>
                            </a>
                            <a href="../admin?action=deleteVoucher&voucherId=${v.id}" style="text-decoration: none">
                                <button class="btn btn-danger btn-sm"><i class="bi bi-trash"></i></button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</div>
<c:remove var="response" scope="session" />
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
    new DataTable('#tableVoucher', {
        layout: {
            topStart: {
                buttons: [
                    {
                        extend: 'copy',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 5
                        }
                    },
                    {
                        extend: 'csv',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 5
                        }
                    },
                    {
                        extend: 'excel',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 5
                        }
                    },
                    {
                        extend: 'pdf',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 5
                        }
                    },
                    {
                        extend: 'print',
                        exportOptions: {
                            columns: ':not(:eq(5))' // xuất tất cả các cột trừ cột thứ 5
                        }
                    },
                ]
            }
        },
        columnDefs: [
            { targets: 5, orderable: false } // Xét ordering cho cột thứ 5
        ]
    });
</script>
</body>
</html>

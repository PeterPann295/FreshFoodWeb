<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="contactDAO" class="database.ContactDAO" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Liên Hệ</title>
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
                <h3 class="h2">Danh Sách Phản Hồi Từ Khách Hàng</h3>
                <span style="color: red">${response}</span>
                <div class="btn-toolbar mb-2 mb-md-0">
                </div>
            </div>
            <table class="table" id="tableContact">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên Người Gửi</th>
                    <th scope="col">Số Điện Thoại</th>
                    <th scope="col">Email</th>
                    <th scope="col">Nội Dung</th>
                    <th scope="col">Thao Tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="contact" items="${contactDAO.selectAll()}">
                    <tr>
                        <td>${contact.contactId}</td>
                        <td>${contact.name}</td>
                        <td>${contact.numberPhone}</td>
                        <td>${contact.email}</td>
                        <td>${contact.content}</td>
                        <td>
                            <a href="../admin?action=contact&contactID=${contact.contactId}" style="text-decoration: none">
                                <button class="btn btn-success btn-sm">Phản Hồi</button>
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
    new DataTable('#tableContact', {
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
            { targets: 5, orderable: false } // Xét ordering cho cột thứ 6
        ]
    });
</script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="customerDAO" class="database.CustomerDao" />

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
                <h3 class="h2">Danh Sách Admin</h3>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="themNguoiDungAdmin.jsp">
                        <button type="button" class="btn btn-sm btn-outline-success">
                            Thêm Admin     </button>
                    </a>
                </div>
            </div>

            <table class="table" id="example">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên Đăng Nhập</th>
                    <th scope="col">Tên Khách Hàng</th>
                    <th scope="col">Số Điện Thoại</th>
                    <th scope="col">Email</th>
                    <th scope="col">Provider</th>
                    <th scope="col"></th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${customerDAO.selectAdmin()}">
                    <tr>
                        <th scope="row">${p.id}</th>
                        <td> <c:if test="${p.username != null}" >
                            ${p.username}
                        </c:if>
                            <c:if test="${p.username == null}" >
                                <c:if test="${p.provider eq 'facebook'}" >
                                    Login with facebook
                                </c:if>
                                <c:if test="${p.provider eq 'google'}" >
                                    Login with google
                                </c:if>
                            </c:if>
                        </td>
                        <td>${p.fullName}</td>
                        <td>
                            <c:if test="${p.numberPhone != null}" >
                                ${p.numberPhone}
                            </c:if>
                            <c:if test="${p.numberPhone == null}" >
                                Không
                            </c:if>
                        </td>
                        <td>${p.email}</td>
                        <td>  <c:if test="${p.provider != null}" >
                            <c:if test="${p.provider eq 'facebook'}" >
                                <div class="badge text-bg-primary text-wrap" style="width: 6rem;">
                                    Facebook
                                </div>
                            </c:if>
                            <c:if test="${p.provider eq 'google'}" >
                                <div class="badge text-bg-danger text-wrap" style="width: 6rem;">
                                    Google
                                </div>
                            </c:if>

                        </c:if>
                            <c:if test="${p.provider == null}" >
                                <div class="badge text-bg-secondary text-wrap" style="width: 6rem;">
                                    Không
                                </div>
                            </c:if>
                        </td>
                        <td style="display: flex">
                            <a href="../admin?action=goUpdateCustomer&customerId=${p.id}"
                               style="text-decoration: none">
                                <button class="btn btn-success btn-sm"><i class="bi bi-pencil-square"></i></button>
                            </a> <a href="../admin?action=deleteCustomer&customerId=${p.id}"
                                    style="text-decoration: none" class="ms-2">
                            <button class="btn btn-success btn-sm"><i class="bi bi-trash"></i></button>
                        </a>
                        </td>
                    </tr>


                </c:forEach>

                </tbody>
            </table>

        </main>
        <c:remove var="username" scope="session"/>
        <c:remove var="err_username" scope="session"/>
        <c:remove var="password" scope="session"/>
        <c:remove var="err_password" scope="session"/>
        <c:remove var="fullName" scope="session"/>
        <c:remove var="err_fullName" scope="session"/>
        <c:remove var="phone" scope="session"/>
        <c:remove var="err_phone" scope="session"/>
        <c:remove var="email" scope="session"/>
        <c:remove var="err_email" scope="session"/>

    </div>
</div>
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
    new DataTable('#example', {
        layout: {
            topStart: {
                buttons: [
                    {
                        extend: 'copy',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'csv',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'excel',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'pdf',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'print',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },

                ]
            }
        },
        columnDefs: [
            { targets: 6, orderable: false } // Xét ordering cho cột thứ 6 (số thứ tự là 5)
        ]
    });

</script>
<c:remove var="response" scope="session"/>
</body>
</html>

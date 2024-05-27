<%@ page import="model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản lý khách hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        .home {
            display: grid;
            grid-template-columns: 0.5fr  2.5fr;
            gap: 30px;
            height: 85vh;
        }
        .adminHeader {
            width: 100%;
        }

        .home-section {
            width: 100%;
            border: 1px solid #4F6F52;
            border-radius: 10px;
            padding: 10px;
            margin: 0 20px 5px 5px;
            max-width: 99%;
        }
        .icon-container {
            background-color: #5cb85c;
            padding: 7px;
            border-radius: 50%;
            display: inline-block;
            color: #F6F5F2;
        }
        .icon-container i {
            font-size: 18px;
        }
        .content-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .content-header h2 {
            margin: 0;
            font-size: 24px;
            color: #333;
        }
        .button-group .btn {
            background-color: #1A4D2E;
            color: #F6F5F2;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button-group .btn:hover {
            background-color: #1A4D2E;
        }
        .table {
            width: 100%;
            background-color: #ffffff;
            border-collapse: collapse;
            border: 1px solid #dee2e6;
        }
        .table th, .table td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #dee2e6;
        }
        .table thead th {
            background-color: #1A4D2E;
            color: #F6F5F2;
            font-weight: bold;
            border-radius: 5px;
        }
        .table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .table tbody tr:hover {
            background-color: #e9ecef;
        }
        .dataTables_info, .dataTables_paginate {
            display: none;
        }
        .icon-container {
            background-color: #28a745;
            border-radius: 50%;
            display: inline-block;
            color: #F6F5F2;
            transition: background-color 0.3s;
        }
        .icon-container i {
            font-size: 16px;
        }
        .icon-container:hover {
            background-color: #218838;
        }
    </style>

</head>
<body>
<div class="home">
    <div class="adminHeader">
        <%@ include file="adminHeader.jsp" %>
    </div>
    <section class="home-section">
    <div class="content-header">
        <h2>Danh Sách Khách Hàng</h2>
        <div class="button-group">
            <a class="btn btn-primary" href="./adminAddUser.jsp">
                <i class="fas fa-user-plus"></i> Thêm khách hàng
            </a>
        </div>
    </div>
    <div class="content-body">
        <table id="customer-table" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Mã khách hàng</th>
                <th>Tên người dùng</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Vai trò</th>
                <th>Chức năng</th>
            </tr>
            </thead>
            <tbody>
            <% List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                if (customers != null) {
                    for (Customer customer : customers) { %>
            <tr>
                <td><%= customer.getId()%></td>
                <td><%= customer.getFullName()%></td>
                <td><%= customer.getEmail()%></td>
                <td><%= customer.getNumberPhone()%></td>
                <td><%= customer.isRole() ? "Quản trị viên" : "Khách hàng" %></td>
                <td>
                    <a href="javascript:void(0);" class="icon-container delete-btn" data-id="<%= customer.getId() %>" title="Xóa">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                    <a href="adminEditCustomer?id=<%= customer.getId() %>" class="icon-container" title="Chỉnh sửa">
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
            </tr>
            <% }
            } %>
            </tbody>
        </table>
    </div>
</section>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function() {
        $('#customer-table').DataTable();

        $('.delete-btn').on('click', function() {
            const customerId = $(this).data('id');
            const row = $('#customer-' + customerId);

            if (confirm('Bạn có chắc chắn muốn xóa khách hàng này không?')) {
                $.ajax({
                    url: 'admin',
                    type: 'POST',
                    data: {
                        action: 'deleteCustomer',
                        id: customerId
                    },
                    success: function(response) {
                        if (response.success) {
                            row.remove();
                        } else {
                            alert('Xóa khách hàng thất bại.');
                        }
                    },
                    error: function() {
                        alert('Có lỗi xảy ra. Vui lòng thử lại.');
                    }
                });
            }
        });
    });
</script>
</body>
</html>

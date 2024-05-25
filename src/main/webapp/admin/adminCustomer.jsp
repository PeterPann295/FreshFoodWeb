<%@ page import="model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản lý khách hàng</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <style>
        .home {
            display: grid;
            grid-template-columns: 0.5fr  2.5fr; /* Chia layout thành 2 cột */
            gap: 50px;
            height: 90vh;
        }
        .adminHeader {
            width: 100%; /* Đặt chiều rộng của phần adminHeader */
        }

        .home-section {
            width: 100%; /* Đặt chiều rộng của phần home-content */
            border: 1px solid #4F6F52;
            border-radius: 10px;
            padding: 10px;
            margin: 0 20px 5px 5px;
            max-width: 95%;
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
        .home-section {
            background-color: #f7f7f7;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
            margin-bottom: 20px;
            background-color: #ffffff;
            border-collapse: collapse;
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
        .icon-container {
            background-color: #28a745;
            padding: 10px;
            border-radius: 50%;
            display: inline-block;
            color: #F6F5F2;
            margin-right: 5px;
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
                    <a href="adminDeleteCustomer?id=<%= customer.getId() %>" class="icon-container" title="Xóa">
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
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.bundle.min.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function() {
        $('#customer-table').DataTable();
    });
</script>
</body>
</html>

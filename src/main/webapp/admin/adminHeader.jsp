<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/23/2024
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý đơn hàng</title>
    <style>
        /* CSS cho header */
        .header {
           background-color: #1A4D2E;
            color: #fff; /* Màu chữ của header */
            padding: 20px; /* Khoảng cách giữa các phần tử trong header */
            text-align: center; /* Căn giữa theo chiều ngang */
            width: 100%;
            border-radius: 10px;
        }

        .logo {
            margin-bottom: 20px; /* Khoảng cách với phần tử dưới */
        }

        .logo a {
            color: #fff; /* Màu chữ của logo */
            text-decoration: none; /* Bỏ gạch chân */
            font-size: 24px; /* Kích thước font cho logo */
        }

        .nav-links {
            list-style: none; /* Ẩn dấu chấm của danh sách */
            margin: 0; /* Không có khoảng cách từng dòng */
            padding: 0; /* Không có khoảng cách đệm */
            display: flex; /* Sắp xếp các phần tử theo chiều dọc */
            flex-direction: column; /* Sắp xếp các phần tử từ trên xuống dưới */
            align-items: center; /* Căn giữa theo chiều ngang */
        }

        .nav-links li {
            margin-bottom: 10px; /* Khoảng cách giữa các mục danh sách */
            background-color: #E0FBE2;
            padding: 10px;
            border-radius: 5px;
            width: 160px;
        }

        .nav-links li a {
            color: #1A4D2E; /* Màu chữ của các liên kết */
            text-decoration: none; /* Bỏ gạch chân */
            transition: color 0.3s; /* Hiệu ứng chuyển động khi di chuột qua */
        }
        .nav-links #logOut {
            margin-top: 240px;
        }

        .nav-links li a:hover {
            color: #ffd700; /* Màu chữ khi di chuột qua */
        }
    </style>
</head>
<body>
<div class="header">
    <div class="logo">
        <a href="adminHome.jsp">ADMIN</a>
    </div>
    <ul class="nav-links">
        <li>
            <a href="#">Trang chủ</a>
        </li>
        <li>
            <a href="adminProduct.jsp">Quản lý sản phẩm</a>
        </li>
        <li>
            <a href="adminOrder.jsp">Quản lý đơn hàng</a>
        </li>
        <li>
            <a href="adminCustomer.jsp">Quản lý tài khoản</a>
        </li>
        <li>
            <a href="adminContact.jsp">Quản lý phản hồi</a>
        </li>
        <li id="logOut">
            <a href="logOutServlet">Đăng xuất</a>
        </li>
    </ul>
</div>
</body>
</html>

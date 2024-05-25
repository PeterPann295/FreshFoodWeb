<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="database.ProductDao" %>
<%@ page import="model.Category" %>
<%@ page import="database.CategoryDao" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> products = new ProductDao().selectAll();
    if (products == null) products = new ArrayList<>();
    Locale locale = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getInstance(locale);
%>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<style>
    .product{
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
    .home-content {
        width: 100%;
        max-width: 95%;
        background-color: #fff;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        padding: 20px;
    }

    /* Title styling */
    .manager-product .title {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    /* Button styling */
    .element-button .btn {
        display: inline-flex;
        align-items: center;
        padding: 8px 12px;
        font-size: 14px;
        text-decoration: none;
        color: #fff;
        background-color: #1A4D2E;
        border-radius: 4px;
        border: none;
        transition: background-color 0.3s;
    }
    .elButton{
        display: flex;
        justify-content: space-between; /* Căn chỉnh các phần tử con theo cả hai phía */
        align-items: center; /* Căn chỉnh phần tử con theo trục dọc */
    }



    .element-button .btn:hover {
        background-color: #0A6847;
    }

    .element-button .btn i {
        margin-right: 5px;
    }

    /* Table styling */
    .table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    .table th,
    .table td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    .table th {
        background-color: #f2f2f2;
    }

    .table tr:hover {
        background-color: #f1f1f1;
    }

    /* Image styling in table */
    .table img {
        max-width: 100px;
        max-height: 100px;
    }

    /* Badge styling */
    .badge {
        display: inline-block;
        padding: 5px 10px;
        border-radius: 4px;
        font-size: 14px;
        font-weight: bold;
    }

    .bg-success {
        background-color: #28a745;
        color: #fff;
    }

    .bg-failed {
        background-color: #dc3545;
        color: #fff;
    }

    /* Action buttons styling */
    .icon-link {
        text-decoration: none;
        color: #1A4D2E;
        font-size: 16px;
        margin-right: 10px;
    }

    .icon-link:hover {
        color: #1A4D2E;
    }

    .icon-wrapper {
        display: inline-flex;
        align-items: center;
    }

    .icon-wrapper i {
        margin-right: 5px;
    }

    .icon-link .fa-right-left,
    .icon-link .fa-pen {
        transition: color 0.3s;
    }

    .icon-link:hover .fa-right-left,
    .icon-link:hover .fa-pen {
        color: #1A4D2E;
    }

    /* Responsive design */
    @media screen and (max-width: 768px) {
        .home-section {
            flex-direction: column;
            align-items: center;
        }

        .home-content {
            width: 100%;
            padding: 15px;
        }

        .table {
            font-size: 14px;
        }

        .element-button .btn {
            font-size: 12px;
            padding: 6px 10px;
        }
    }

</style>
<body>
<div class="product">
<div class="adminHeader">
    <%@ include file="adminHeader.jsp" %>
</div>

<section class="home-section">
    <div class="home-content">
        <div class="manager-product">
            <div class="title">Danh Sách Sản Phẩm</div>
            <div class="elButton">
                <div class="row element-button">
                    <div class="col-sm-2">
                        <a class="btn btn-add btn-sm" href="adminAddProduct.jsp" title="Thêm">
                            <i class="fas fa-plus"></i>
                            Thêm sản phẩm</a>
                    </div>
                </div>
                <div class="row element-button">
                    <div class="col-sm-2">
                        <a class="btn btn-add btn-sm" href="adminAddParentCategory.jsp" title="Thêm">
                            <i class="fas fa-plus"></i>
                            Thêm danh mục </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table id="table-id" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Tình trạng</th>
                            <th scope="col">Giá tiền</th>
                            <th scope="col">Giá giảm</th>
                            <th scope="col">Danh mục</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (Product product : products) { %>
                        <tr>
                            <th scope="row"><%= product.getId() %></th>
                            <td><%= product.getName() %></td>
                            <td><img src="<%= product.getImageUrl() %>" style="max-width: 100px; max-height: 100px;"></td>
                            <% if (product.isAvailable()) { %>
                            <td><span class="badge bg-success">Còn hàng</span></td>
                            <% } else { %>
                            <td><span style="background-color: #efbfbf !important; color: #790202 !important;" class="badge bg-failed">Hết hàng</span></td>
                            <% } %>
                            <td><%= numberFormat.format(product.getPrice()) %><sup>đ</sup></td>
                            <td><%= numberFormat.format(product.getFinalPrice()) %>đ</td>
                            <td><%= product.getCategory().getName() %></td>
                            <% String status = (product.getStatus() == 1) ? "Hiện" : "Ẩn"; %>
                            <td><%= status %></td>
                            <td>
                                <button style="border: none; background: none;" title="Đổi trạng thái" onclick="changeStatusProduct(<%= product.getId() %>)" class="icon-link">
                                    <i class="icon-wrapper">
                                        <i class="fa-solid fa-right-left"></i>
                                    </i>
                                </button>
                                <a title="Chỉnh sửa" href="page-admin-edit-product?product_id=<%= product.getId() %>" class="icon-link">
                                    <i class="icon-wrapper">
                                        <i class="fas fa-pen"></i>
                                    </i>
                                </a>
                            </td>
                        </tr>
                        <% } %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</div>

<!-- Include các file JS cần thiết -->
</body>
</html>

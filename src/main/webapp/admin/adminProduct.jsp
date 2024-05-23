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
    <!-- Include các file CSS và JS cần thiết -->
</head>
<body>
<!-- Include header -->
<%@include file="adminHeader.jsp" %>

<section class="home-section">
    <div class="home-content">
        <div class="manager-product">
            <div class="title">Danh Sách Sản Phẩm</div>
            <div class="row element-button">
                <div class="col-sm-2">
                    <a class="btn btn-add btn-sm" href="adminAddProduct.jsp" title="Thêm">
                        <i class="fas fa-plus"></i>
                        Thêm sản phẩm</a>
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

<!-- Include các file JS cần thiết -->
</body>
</html>

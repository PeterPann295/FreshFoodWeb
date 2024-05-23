<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="database.CategoryDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Thêm Sản Phẩm</title>
</head>
<body>
<%@include file="adminHeader.jsp" %>
<section class="home-section">
    <div class="home-content">
        <div class="manager-product">
            <div class="title">Thêm Sản Phẩm</div>
            <form class="row" action="AdminServlet?action=addProduct" method="post" enctype="multipart/form-data">
                <div class="form-group col-md-3" style="display: none" type="hidden">
                    <input class="form-control" value="" type="hidden" placeholder="" name="id">
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Tên sản phẩm</label>
                    <input class="form-control" type="text" name="name" required>
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Mô tả sản phẩm</label>
                    <textarea class="form-control" name="description" required></textarea>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Giá sản phẩm (VNĐ)</label>
                    <input class="form-control" type="number" name="price" required>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Số lượng</label>
                    <input class="form-control" type="number" name="available" required>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Danh mục</label>
                    <select class="form-control" name="category" required>
                        <option value="" selected disabled>--- Chọn danh mục ---</option>
                        <% List<Category> categories = new CategoryDao().selectAll(); %>
                        <% for (Category category : categories) { %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Giảm giá (%)</label>
                    <input class="form-control" type="number" name="discountPercent">
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Hình ảnh sản phẩm</label>
                    <input class="form-control" type="file" name="image" required>
                </div>
                <!-- Các trường khác tùy ý -->
                <button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
            </form>
        </div>
    </div>
</section>
</body>
</html>

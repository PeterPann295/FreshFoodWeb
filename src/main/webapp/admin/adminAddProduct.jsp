<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="database.CategoryDao" %>
<%@ page import="model.Discount" %>
<%@ page import="database.DiscountDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Thêm Sản Phẩm</title>
</head>
<style>
    .home {
        display: grid;
        grid-template-columns: 0.5fr  2.5fr;
        gap: 50px;
        height: 90vh;
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
        max-width: 95%;
    }
    .manager-product .title {
        font-size: 28px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }

    form {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .form-group {
        width: 100%;
    }

    @media (min-width: 768px) {
        .form-group.col-md-3 {
            width: calc(33.333% - 20px);
        }

        .form-group.col-md-6 {
            width: calc(66.666% - 20px);
        }
    }

    label.control-label {
        font-weight: bold;
        margin-bottom: 5px;
        display: block;
        color: #555;
    }

    input.form-control,
    textarea.form-control,
    select.form-control {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ddd;
        border-radius: 4px;
        transition: border-color 0.3s;
    }

    input.form-control:focus,
    textarea.form-control:focus,
    select.form-control:focus {
        border-color: #007bff;
    }

    textarea.form-control {
        height: 100px;
        resize: vertical;
    }

    button.btn {
        display: block;
        width: 100%;
        padding: 10px;
        font-size: 16px;
        font-weight: bold;
        color: #fff;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button.btn:hover {
        background-color: #0056b3;
    }

    @media screen and (max-width: 768px) {
        .home-section {
            padding: 15px;
        }

        .form-group {
            width: 100%;
        }

        button.btn {
            width: 100%;
        }
    }

</style>
<body>
<div class="home">
    <div class="adminHeader">
        <%@ include file="adminHeader.jsp" %>
    </div>

<section class="home-section">
    <div class="home-content">
        <div class="manager-product">
            <div class="title">Thêm Sản Phẩm</div>
            <form action="<%= request.getContextPath() %>/admin?action=addProduct" method="post" enctype="multipart/form-data">
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
                <div class="form-group col-md-6">
                    <label class="control-label">Đơn vị</label>
                    <textarea class="form-control" name="unit" required></textarea>
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Trọng lượng</label>
                    <textarea class="form-control" name="weight" required></textarea>
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
                    <label for="discount">Giảm giá:</label><br>
                    <select id="discount" name="discount">
                        <option value="none">Không</option>
                        <%-- Thay đổi danh sách giảm giá tùy theo cách bạn lấy dữ liệu --%>
                        <% List<Discount> discounts = new DiscountDao().selectAll(); %>
                        <% for (Discount discount : discounts) { %>
                        <option value="<%= discount.getId() %>"><%= discount.getName() %></option>
                        <% } %>
                    </select><br>
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Hình ảnh sản phẩm</label>
                    <input class="form-control" type="file" name="image" required>
                </div>
                <button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
            </form>
        </div>
    </div>
</section>
</div>
</body>


</html>

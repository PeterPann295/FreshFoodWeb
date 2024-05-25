<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Thêm Danh Mục Cha</title>
</head>
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
    .add-category-form {
        max-width: 600px;
        margin: 50px auto;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #f9f9f9;
    }

    .add-category-form h2 {
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
    }

    .form-group input[type="text"],
    .form-group input[type="url"] {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .form-group button {
        padding: 10px 15px;
        border: none;
        background-color: #28a745;
        color: #fff;
        border-radius: 4px;
        cursor: pointer;
    }

    .form-group button:hover {
        background-color: #218838;
    }
</style>
<body>
<div class="home">
    <div class="adminHeader">
        <%@ include file="adminHeader.jsp" %>
    </div>
    <section class="home-section">
        <div class="add-category-form">
            <h2>Thêm Danh Mục Cha</h2>
            <form action="<%= request.getContextPath() %>/admin?action=addParentCategory" method="post" enctype="multipart/form-data">
            <div class="form-group">
                    <label for="name">Tên danh mục cha</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="imageURL">Ảnh danh mục</label>
                    <input type="file" id="imageURL" name="imageURL" required>
                </div>
                <div class="form-group">
                    <button type="submit">Thêm danh mục cha</button>
                </div>
            </form>
        </div>
    </section>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>

<jsp:useBean id="discountDAO" class="database.DiscountDao"
             />
<jsp:useBean id="categoryDAO" class="database.CategoryDao"
             />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <%@ include file="../layouts/common.jsp"%>
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../assets/css/dangKi.css">
</head>
<body>
<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="container_form">
                <form class="form-container" action="../admin?action=addProduct" method="post"
                      enctype="multipart/form-data">

                    <h1 class="text-center">Thêm sản phẩm</h1>

                    <div class="mb-3">
                        <label for="productName" class="form-label">Tên Sản Phẩm</label>
                        <input type="text" class="form-control" id="productName"
                               name="productName" placeholder="Nhập tên sản phẩm" value="${productName}">

                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Giá Sản Phẩm</label> <input
                            type="number" class="form-control" id="price" name="price"
                            placeholder="Nhập giá sản phẩm" value="${price}">
                        <div class="text-danger">${err_price}</div>

                    </div>

                    <div class="mb-3">
                        <label for="unit" class="form-label">Đơn Vị Tính</label>
                        <select class="form-select" id="unit" name="unit">
                            <option value="0.5 Kg">0.5 KG</option>
                            <option value="1 Kg">1 KG</option>
                            <option value="Chai">Chai</option>
                            <option value="Lon">Lon</option>
                            <option value="Gói">Gói</option>
                            <option value="Khay">Khay</option>
                            <option value="24 Chai">24 Chai</option>
                            <option value="Bao">Bao</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="weight" class="form-label">Trọng Lượng</label> <input
                            type="text" class="form-control" id="weight" name="weight"
                            placeholder="Nhập trọng lượng">
                    </div>

                    <div class="mb-3">
                        <label for="imgProduct" class="form-label">Hình Ảnh Sản
                            Phẩm</label> <input type="file" class="form-control" id="imgProduct"
                                                name="imgProduct" placeholder="Nhập mã sản phẩm">

                    </div>

                    <div class="mb-3">

                        <label for="availables" class="form-label">Trình Trạng: </label>
                        <select class="form-select" id="availables" name="availables">
                            <option value="true">Còn Hàng</option>
                            <option value="false">Hết Hàng</option>

                        </select>

                    </div>

                    <div class="mb-3">
                        <label for="category" class="form-label">Chọn Thể Loại
                            Sản Phẩm</label> <select class="form-select" id="category"
                                                     name="category">
                        <c:forEach var="p" items="${categoryDAO.selectAll()}">
                            <option value="${p.id}">${p.name}</option>
                        </c:forEach>
                    </select>

                        <div class="mb-3">

                            <label for="discount" class="form-label">Chọn Chương
                                Trình Giảm Giá: </label> <select class="form-select" id="discount"
                                                                 name="discount">
                            <option value="none">Không</option>
                            <c:forEach var="p" items="${discountDAO.selectAll()}">
                                <option value="${p.id}">${p.name}</option>
                            </c:forEach>
                        </select>

                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Mô Tả Sản
                            Phẩm</label>
                        <textarea type="text" class="form-control" id="description"
                                  name="description" placeholder="Nhập mô tả sản phẩm" rows="5"
                                  cols="50"> ${description} </textarea>

                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary width-btn">Thêm
                            Sản Phẩm</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>
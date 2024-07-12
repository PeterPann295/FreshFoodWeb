<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="importProduct" class="database.ImportProductDao" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <%@ include file="/layouts/common.jsp"%>
    <style>
        .card:hover {
            border: 2px solid;
            border-color: #28a745;
        }

        .discount-percentage {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: red;
            color: white;
            padding: 5px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<%@ include file="/layouts/header.jsp"%>

<div class="row ms-4 mt-4">

    <div class="col-lg-4  mt-4">

        <div class="container">
            <img alt="" src="${productDetail.imageUrl}"
                 style="width: 390px; height: 390px">

            <h5 class="mt-4">Mô Tả</h5>

            <h6 class="mt-4">${productDetail.name}</h6>

            <p class="mt-4 lh-base" style="text-align: justify;">${productDetail.description}</p>
        </div>

    </div>

    <div class="col-lg-8">



        <table class="table">
            <thead>

            <tr>
                <th>
                    <h3>${productDetail.name}</h3>
                </th>
            </tr>


            </thead>
            <tbody>

            <tr>
                <td>Giá Bán</td>
                <td><c:choose>
                    <c:when test="${productDetail.discount != null}">
									<span class="text-success"> <fmt:formatNumber
                                            value="${productDetail.getFinalPrice()}" type="currency"
                                            currencyCode="VND" minFractionDigits="0" />
									</span>
                        <span style="text-decoration: line-through; padding-left: 5px">
										<fmt:formatNumber value="${productDetail.price}"
                                                          type="currency" currencyCode="VND" minFractionDigits="0" />
									</span>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${productDetail.price}"
                                          type="currency" currencyCode="VND" minFractionDigits="0" />
                    </c:otherwise>
                </c:choose></td>
            </tr>


            <tr>
                <td>Số lượng trong kho</td>
                <td>${importProduct.selectToTalProductInStock(productDetail.id)}</td>
            </tr>

            <tr>
                <td>Vận chuyển</td>
                <td>Miễn phí giao hàng cho đơn từ 300.000đ. Giao hàng trong 2
                    giờ.</td>
            </tr>

            <tr>
                <td>Đơn vị tính</td>
                <td>${productDetail.unit}</td>
            </tr>

            <tr>
                <td>Số lượng</td>
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-info btn-sm"
                                onclick="decrement()">
                            <a><i class="bi bi-dash icon"></i></a>
                        </button>
                        <input type="text" class="form-control text-center input-sm"
                               id="numberInput" name="amount" value="1" min="1" size="1" max="${importProduct.selectToTalProductInStock(productDetail.id)}" readonly>
                        <button type="button" class="btn btn-info btn-sm"
                                onclick="increment()">
                            <a><i class="bi bi-plus icon"></i></a>
                        </button>
                    </div>
                </td>
            </tr>


            </tbody>
        </table>
        <c:if test="${importProduct.selectToTalProductInStock(productDetail.id) > 0}">
        <button class="ms-1 btn btn-success add-to-cart-btn"
                data-product-id="${productDetail.id}">
            <i class="bi bi-cart3"></i> Thêm Vào Giỏ
        </button>
        </c:if>
        <c:if test="${importProduct.selectToTalProductInStock(productDetail.id) < 1}">
            <button class="ms-1 btn btn-secondary"
                    >
                <i class="bi bi-cart3"></i> Đã hết hàng
            </button>
        </c:if>


        <table class="table mt-4">
            <thead>
            <tr>
                <th>
                    <h5>Thông tin</h5>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Xuất xứ</td>
                <td>Việt Nam</td>
            </tr>
            <tr>
                <td>Thành phần</td>
                <td>Đang cập nhật</td>
            </tr>
            <tr>
                <td>Hướng dẫn sử dụng</td>
                <td>Đang cập nhật</td>
            </tr>
            <tr>
                <td>Bảo quản</td>
                <td>Đang cập nhật</td>
            </tr>
            </tbody>

        </table>
    </div>
</div>

<div class="ms-4 mt-4">

    <h4 style="margin-left: 20px">Sản phẩm liên quan</h4>

    <div class="row" style="margin-left: 30px">
        <c:forEach var="product" items="${productRelate}">
            <div class="col-lg-4 col-md-6 mb-4"
                 style="width: 216px; height: 355px">

                <c:choose>
                    <c:when test="${product.discount != null}">
                        <div class="card">
                            <a href="customer?action=productDetail&productId=${product.id}"><img class="card-img-top"
                                                                                         src="${product.imageUrl}" alt=""></a>
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="#" style="text-decoration: none">
                                            ${product.name} </a>
                                </h5>
                                <p class="mt-1">ĐVT: ${product.unit}</p>
                                <p>
									<span class="text-success"> <fmt:formatNumber
                                                value="${product.getFinalPrice()}" type="currency"
                                                currencyCode="VND" minFractionDigits="0"/>
										</span> <span
                                        style="text-decoration: line-through; padding-left: 5px">
											<fmt:formatNumber value="${product.price}" type="currency"
                                                              currencyCode="VND" minFractionDigits="0"/>
										</span>
                                </p>
                                <span class="discount-percentage"> Giảm
										${product.discount.percent}% </span>
                                <button class="ms-1 btn btn-success add-to-cart-btn-one"
                                        data-product-id="${product.id}">
                                    <i class="bi bi-cart3"></i> Thêm Vào Giỏ
                                </button>
                            </div>

                        </div>

                    </c:when>
                    <c:otherwise>
                        <div class="card">
                                                      <a href="customer?action=productDetail&productId=${product.id}"><img class="card-img-top"
                                                                                         src="${product.imageUrl}" alt=""></a>
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="#" style="text-decoration: none">
                                            ${product.name} </a>
                                </h5>
                                <p class="mt-1">ĐVT: ${product.unit}</p>
                                <p>
										<span class="text-success"> <fmt:formatNumber
                                                value="${product.getFinalPrice()}" type="currency"
                                                currencyCode="VND"  minFractionDigits="0"/>
										</span>
                                </p>
                                <button class="ms-1 btn btn-success add-to-cart-btn-one"
                                        data-product-id="${product.id}">
                                    <i class="bi bi-cart3"></i> Thêm Vào Giỏ
                                </button>
                            </div>

                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </c:forEach>
    </div>

</div>

<%@ include file="/layouts/footer.jsp"%>
</body>
<script>
    function increment() {
        var inputElement = document.getElementById('numberInput');
        var currentValue = parseInt(inputElement.value, 10);
        var maxValue = parseInt(inputElement.max);

        if (currentValue < maxValue) {
            inputElement.value = currentValue + 1;
        }
    };

    function decrement() {
        var inputElement = document.getElementById('numberInput');
        var currentValue = parseInt(inputElement.value, 10);
        if (currentValue > 1) {
            inputElement.value = currentValue - 1;
        }
    };
    $(document).ready(function() {
        $(".add-to-cart-btn").click(function() {
            // Lưu trữ $(this) vào biến để sử dụng trong hàm success
            var addButton = $(this);

            // Kiểm tra session của khách hàng
            $.ajax({
                type: "post",
                url: "customer?action=checkLoginCustomer",
                success: function(response) {
                    if (response.isLoggedIn) {
                        // Nếu đã đăng nhập, thực hiện AJAX request để thêm vào giỏ hàng
                        var productId = addButton.data("product-id"); // Sử dụng biến addButton thay vì $(this)
                        var amount = document.getElementById('numberInput').value;
                        console.log(amount)
                        $.ajax({
                            type: "post",
                            url: "customer?action=addToCart",
                            data: {
                                productId: productId,
                                quantity: amount
                            },
                            success: function(response) {
                                console.log(response.cartSize)
                                $("#cart-size").text(response.cartSize);
                            },
                            error: function(error) {
                                console.log("Error: " + error);
                            }
                        });
                    } else {
                        window.location.href = "dangNhap.jsp";
                    }
                },
                error: function(error) {
                    console.log("Error: " + error);
                }
            });
        });
        $(".add-to-cart-btn-one").click(function() {
            // Lưu trữ $(this) vào biến để sử dụng trong hàm success
            var addButton = $(this);

            // Kiểm tra session của khách hàng
            $.ajax({
                type: "post",
                url: "customer?action=checkLoginCustomer",
                success: function(response) {
                    if (response.isLoggedIn) {
                        // Nếu đã đăng nhập, thực hiện AJAX request để thêm vào giỏ hàng
                        var productId = addButton.data("product-id"); // Sử dụng biến addButton thay vì $(this)
                        var amount = 1;
                        console.log(amount)
                        $.ajax({
                            type: "post",
                            url: "customer?action=addToCart",
                            data: {
                                productId: productId,
                                quantity: amount
                            },
                            success: function(response) {
                                console.log(response.cartSize)
                                $("#cart-size").text(response.cartSize);
                            },
                            error: function(error) {
                                console.log("Error: " + error);
                            }
                        });
                    } else {
                        window.location.href = "dangNhap.jsp";
                    }
                },
                error: function(error) {
                    console.log("Error: " + error);
                }
            });
        });

    });
</script>
</html>
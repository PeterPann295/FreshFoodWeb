<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/11/2024
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="parentCategoryDAO" class="database.ParentCategoryDao"
             scope="page" />
<jsp:useBean id="productDAO" class="database.ProductDao" scope="page" />
<jsp:useBean id="importProduct" class="database.ImportProductDao" />
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
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
<div>
    <%@ include file="layouts/header.jsp"%>
    <div id="myCarousel" class="carousel slide mb-6"
         data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img alt="" src="assets/images/fresh.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption text-start">
                        <h1>Example headline.</h1>
                        <p class="opacity-75 text-success"
                           style="font-size: 33px; margin-bottom: 50px;">
                            "Hãy yêu thương bản thân <br> mình, bằng những sản <br>
                            phẩm chất lượng."
                        </p>
                        <p style="margin-bottom: 80px;">
                            <a class="btn btn-lg btn-primary" href="dangNhap.jsp">Đăng nhập ngay!</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img alt="" src="assets/images/slide2.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption">
                        <p style="margin-right: 50px; margin-bottom: 80px;">
                            <a class="btn btn-lg btn-primary" href="sanPham.jsp">Xem Ngay</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img alt="" src="assets/images/food.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption text-end"
                         style="margin-bottom: 80px; margin-right: 45px;">
                        <p style="margin-right: 20px">
                            <a class="btn btn-lg btn-primary" href="sanPham.jsp">Xem Ngay</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button"
                data-bs-target="#myCarousel" data-bs-slide="prev">
				<span class="carousel-control-prev-icon"
                      style="background-color: green" aria-hidden="true"></span> <span
                class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button"
                data-bs-target="#myCarousel" data-bs-slide="next">
				<span class="carousel-control-next-icon"
                      style="background-color: green" aria-hidden="true"></span> <span
                class="visually-hidden">Next</span>
        </button>


    </div>
    <hr>
</div>
<div style="margin: 40px;">

    <h3 class="text-success">Danh Mục Nổi Bật</h3>
    <hr class="border border-success border-1 opacity-75">

    <div class="row" style="margin-left: 20px">
        			<c:forEach var="p" items="${parentCategoryDAO.selectAll()}">
        				<div class="col-lg-4 col-md-6 mb-4 border-success"
        					style="width: 160px; height: 150px">
        					<div class="card h-110">
        						<a
        							href="customer?action=searchByParentCategory&parentCateId=${p.id}"
        							style="text-decoration: none" class="text-success"><img
        							class="card-img-top" src="${p.imageURL}" alt="">
        							<div class="card-body">
        								<h6 class="text-center">${p.name}</h6>
        								<p class="card-text"></p>
        							</div> </a>
        					</div>
        				</div>
        			</c:forEach>
    </div>
</div>
<div style="margin: 40px; margin-top: 40px;">
    <h3 class="text-success">Sản Phẩm Mới Nhất</h3>
    <hr class="border border-success border-1 opacity-75">

    <div class="row" style="margin-left: 30px">
        			<c:forEach var="product" items="${productDAO.selectNewestProducts()}">
        				<c:if test="${importProduct.selectToTalProductInStock(product.id) < 1}" >
						<div class="col-lg-4 col-md-6 mb-4 mt-2"
							 style="width: 216px; height: 355px">
									<div class="card">
										<a href="customer?action=productDetail&productId=${product.id}"><img
												class="card-img-top" src="${product.imageUrl}" alt="" style="filter: grayscale(1)"></a>
										<div class="card-body">
											<h5 class="card-title">
												<a href="#" style="text-decoration: none">
														${product.name} </a>
											</h5>
											<p class="mt-1">ĐVT: ${product.unit}</p>
											<p>
        										<span class="text-success"> <fmt:formatNumber
														value="${product.getFinalPrice()}" type="currency"
														currencyCode="VND" minFractionDigits="0" />
        										</span> <span
													style="text-decoration: line-through; padding-left: 5px">
        											<fmt:formatNumber value="${product.price}" type="currency"
																	  currencyCode="VND" minFractionDigits="0" />
        										</span>
											</p>
											<span class="discount-percentage">
        										Sold out  </span>
											<button class="ms-1 btn btn-secondary"
													data-product-id="${product.id}">
												<i class="bi bi-cart3"></i> Đã hết hàng
											</button>
										</div>
									</div>
						</div>
						</c:if>
						<c:if test="${importProduct.selectToTalProductInStock(product.id) > 0}" >

												<div class="col-lg-4 col-md-6 mb-4 mt-2"
        					style="width: 216px; height: 355px">

        					<c:choose>
        						<c:when test="${product.discount != null}">
        							<div class="card">
        								<a href="customer?action=productDetail&productId=${product.id}"><img
        									class="card-img-top" src="${product.imageUrl}" alt=""></a>
        								<div class="card-body">
        									<h5 class="card-title">
        										<a href="#" style="text-decoration: none">
        											${product.name} </a>
        									</h5>
        									<p class="mt-1">ĐVT: ${product.unit}</p>
        									<p>
        										<span class="text-success"> <fmt:formatNumber
        												value="${product.getFinalPrice()}" type="currency"
        												currencyCode="VND" minFractionDigits="0" />
        										</span> <span
        											style="text-decoration: line-through; padding-left: 5px">
        											<fmt:formatNumber value="${product.price}" type="currency"
        												currencyCode="VND" minFractionDigits="0" />
        										</span>
        									</p>
        									<span class="discount-percentage"> Giảm
        										${product.discount.percent}% </span>
											<button class="ms-1 btn btn-success add-to-cart-btn"
													data-product-id="${product.id}">
												<i class="bi bi-cart3"></i> Thêm Vào Giỏ
											</button>
        								</div>

        							</div>

        						</c:when>
        						<c:otherwise>
        							<div class="card">
        								<a href="customer?action=productDetail&productId=${product.id}"><img
        									class="card-img-top" src="${product.imageUrl}" alt=""></a>
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
        										</span>
        									</p>

											<button class="ms-1 btn btn-success add-to-cart-btn"
											   data-product-id="${product.id}">
												<i class="bi bi-cart3"></i> Thêm Vào Giỏ
											</button>
        								</div>

        							</div>
        						</c:otherwise>
        					</c:choose>

        				</div>
						</c:if>
        			</c:forEach>
    </div>
	<input type="hidden" id="amount" value="1">
</div>
<div style="margin: 40px; margin-top: 40px;">
	<h3 class="text-success">Khuyến Mãi Hấp Dẫn</h3>
	<hr class="border border-success border-1 opacity-75">

	<div class="row" style="margin-left: 30px">
		<c:forEach var="product" items="${productDAO.selectProductHasDiscount()}">
			<c:if test="${importProduct.selectToTalProductInStock(product.id) < 1}" >
				<div class="col-lg-4 col-md-6 mb-4 mt-2"
					 style="width: 216px; height: 355px">
					<div class="card">
						<a href="customer?action=productDetail&productId=${product.id}"><img
								class="card-img-top" src="${product.imageUrl}" alt="" style="filter: grayscale(1)"></a>
						<div class="card-body">
							<h5 class="card-title">
								<a href="#" style="text-decoration: none">
										${product.name} </a>
							</h5>
							<p class="mt-1">ĐVT: ${product.unit}</p>
							<p>
        										<span class="text-success"> <fmt:formatNumber
														value="${product.getFinalPrice()}" type="currency"
														currencyCode="VND" minFractionDigits="0" />
        										</span> <span
									style="text-decoration: line-through; padding-left: 5px">
        											<fmt:formatNumber value="${product.price}" type="currency"
																	  currencyCode="VND" minFractionDigits="0" />
        										</span>
							</p>
							<span class="discount-percentage">
        										Sold out  </span>
							<button class="ms-1 btn btn-secondary"
									data-product-id="${product.id}">
								<i class="bi bi-cart3"></i> Đã hết hàng
							</button>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${importProduct.selectToTalProductInStock(product.id) > 0}" >

				<div class="col-lg-4 col-md-6 mb-4 mt-2"
					 style="width: 216px; height: 355px">

					<c:choose>
						<c:when test="${product.discount != null}">
							<div class="card">
								<a href="customer?action=productDetail&productId=${product.id}"><img
										class="card-img-top" src="${product.imageUrl}" alt=""></a>
								<div class="card-body">
									<h5 class="card-title">
										<a href="#" style="text-decoration: none">
												${product.name} </a>
									</h5>
									<p class="mt-1">ĐVT: ${product.unit}</p>
									<p>
        										<span class="text-success"> <fmt:formatNumber
														value="${product.getFinalPrice()}" type="currency"
														currencyCode="VND" minFractionDigits="0" />
        										</span> <span
											style="text-decoration: line-through; padding-left: 5px">
        											<fmt:formatNumber value="${product.price}" type="currency"
																	  currencyCode="VND" minFractionDigits="0" />
        										</span>
									</p>
									<span class="discount-percentage"> Giảm
        										${product.discount.percent}% </span>
									<button class="ms-1 btn btn-success add-to-cart-btn"
											data-product-id="${product.id}">
										<i class="bi bi-cart3"></i> Thêm Vào Giỏ
									</button>
								</div>

							</div>

						</c:when>
						<c:otherwise>
							<div class="card">
								<a href="customer?action=productDetail&productId=${product.id}"><img
										class="card-img-top" src="${product.imageUrl}" alt=""></a>
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
        										</span>
									</p>

									<button class="ms-1 btn btn-success add-to-cart-btn"
											data-product-id="${product.id}">
										<i class="bi bi-cart3"></i> Thêm Vào Giỏ
									</button>
								</div>

							</div>
						</c:otherwise>
					</c:choose>

				</div>
			</c:if>
		</c:forEach>
	</div>
</div>

<script>
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
						var amount = document.getElementById('amount').value;
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
</body>
</html>

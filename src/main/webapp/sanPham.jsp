<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="productDAO" class="database.ProductDao" scope="page" />
<jsp:useBean id="parentCategoryDAO" class="database.ParentCategoryDao"
             scope="page" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <style>
        .card-hover:hover {
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
<jsp:include page="/layouts/header.jsp" />

<div class="row mt-4">

    <jsp:include page="/layouts/filterProduct.jsp" />

    <div class="col-lg-9">
        <div class="btn-toolbar mb-2 ">
            <a href="bolocsanpham?hanhDong=giaGiam&trang=sanPham" style="margin-right: 5px">
                <button type="button" class="btn btn-sm btn-outline-success">
                    Giá Giảm Dần</button>
            </a>
            <a href="bolocsanpham?hanhDong=giaTang&trang=sanPham" style="margin-right: 5px">
                <button type="button" class="btn btn-sm btn-outline-success">
                    Giá Tăng Dần</button>
            </a>
            <a href="bolocsanpham?hanhDong=AZ&trang=sanPham" style="margin-right: 5px">
                <button type="button" class="btn btn-sm btn-outline-success">
                    A-Z</button>
            </a>
            <a href="bolocsanpham?hanhDong=ZA&trang=sanPham" style="margin-right: 5px">
                <button type="button" class="btn btn-sm btn-outline-success">
                    Z-A</button>
            </a>
        </div>
        <input type="hidden" id="numberInput" value="1">

        <div class="row" style="margin-left: 30px">
            <c:forEach var="product" items="${productDAO.selectAll()}">
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

</div>

</body>
<script src="javascript/scriptAjax2.js"></script>

</html>
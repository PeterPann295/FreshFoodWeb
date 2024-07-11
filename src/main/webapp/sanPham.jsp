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
        .active {
            background-color: #28a745;
            color: white;
            border-color: #28a745;
        }

    </style>

</head>
<body>
<jsp:include page="layouts/header.jsp" />
<div class="row mt-4">

    <jsp:include page="/layouts/filterProduct.jsp" />
    <input type="hidden" id="amount" value="1">
    <div class="col-lg-9">
        <div class="btn-toolbar mb-2  " style="margin-left: 8px">
            <button type="button" id="sortPriceDesc" class="btn-sort btn btn-sm btn-outline-success" style="margin-right: 5px">Giá Giảm Dần</button>
            <button type="button" id="sortPriceAsc" class="btn-sort btn btn-sm btn-outline-success" style="margin-right: 5px">Giá Tăng Dần</button>
            <button type="button" id="sortAZ" class="btn-sort btn btn-sm btn-outline-success" style="margin-right: 5px">A-Z</button>
            <button type="button" id="sortZA" class="btn-sort btn btn-sm btn-outline-success" style="margin-right: 5px">Z-A</button>

        </div>
        <input type="hidden" id="numberInput" value="1">

        <div class="row" id="productContainer" >
            <c:forEach var="product" items="${listProductPaging}">
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
            <div style="text-align: center" class="mt-4 mb-4">
                <c:forEach begin="1" end="${endPage}" var="i">
                    <a class="btn btn-primary" href="/customer?action=goListProduct&index=${i}" role="button"> ${i} </a>                </c:forEach>
            </div>

        </div>


    </div>

</div>

</body>
<script>
    let sortAction = '';
    $(document).ready(function() {
        $('#productContainer').on('click', '.add-to-cart-btn-one', function() {
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
        // Lắng nghe sự kiện thay đổi của checkbox và radio button
        $('input[type="checkbox"], input[type="radio"]').change(function() {
            applyFilters();
        });

        $('.btn-sort').click(function() {
            // Remove the 'active' class from all buttons
            $('.btn-sort').removeClass('active');

            // Add the 'active' class to the clicked button
            $(this).addClass('active');

            // Perform the AJAX request
            let action = '';
            if (this.id === 'sortPriceDesc') sortAction = 'giaGiam';
            else if (this.id === 'sortPriceAsc') sortAction = 'giaTang';
            else if (this.id === 'sortAZ') sortAction = 'AZ';
            else if (this.id === 'sortZA') sortAction = 'ZA';
            applyFilters();
        });

    });

    function applyFilters() {
        // Thu thập các giá trị đã chọn
        var categories = [];
        $('input[name="category"]:checked').each(function() {
            categories.push($(this).val());
        });
        console.log(categories)
        var price = $('input[name="price"]:checked').val();
        var discount = $('input[name="discount"]:checked').val();

        // Gửi yêu cầu Ajax
        $.ajax({
            url: 'customer?action=filterProduct',
            type: 'POST',
            data: {
                categories: categories,
                price: price,
                discount: discount,
                sortAction: sortAction
            },
            traditional: true,
            success: function(response) {
                console.log("da vao day ")
                var productContainer = $('#productContainer');
                productContainer.html("");
                response.forEach(function(product) {
                    console.log("da vao day 2")
                    var productHtml = createProductHtml(product);
                    productContainer.append(productHtml);
                });
            }
        });
    }

    function createProductHtml(product) {
        var discountHtml = '';
        console.log(product)
        console.log(product.name+"la ten cua no")
        if (product.discount != null) {
            discountHtml = '<span class="discount-percentage"> Giảm ' + product.discount.percent + '% </span>';
        }

        var finalPrice = product.price - (product.price * (product.discount ? product.discount.percent / 100 : 0));
        var formattedOrigin= new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.price);
        var originalPriceHtml = product.discount ? '<span style="text-decoration: line-through; padding-left: 5px">' + formattedOrigin + ' </span>' : '';
        var formattedPriceFinal = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(finalPrice);


        var productHtml = `
    <div class="col-lg-4 col-md-6 mb-4" style="width: 216px; height: 355px">
        <div class="card">
            <a href="customer?action=productDetail&productId=`+ product.id +`"><img class="card-img-top" src="`+product.imageUrl+`" alt=""></a>
            <div class="card-body">
                <h5 class="card-title">
                    <a href="customer?action=productDetail&productId=`+ product.id +`" style="text-decoration: none">`+ product.name +`</a>
                </h5>
                <p class="mt-1">ĐVT: `+ product.unit +`</p>
                <p>
                    <span class="text-success">`+ formattedPriceFinal  +` </span> `+ originalPriceHtml +`
                </p>
                `+ discountHtml +`
                <button class="ms-1 btn btn-success add-to-cart-btn-one" data-product-id="`+ product.id +`">
                    <i class="bi bi-cart3"></i> Thêm Vào Giỏ
                </button>
            </div>
        </div>
    </div>`;

        return productHtml;
    }
</script>
</html>
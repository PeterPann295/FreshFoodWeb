<%--
  Created by IntelliJ IDEA.
  User: lemin
  Date: 5/16/2024
  Time: 7:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="cartDao" class="database.CartDao" />
<jsp:useBean id="cartItemDao" class="database.CartItemDao" />
<c:remove var="prices" scope="session" />
<%--<c:set var="scheme" value="${pageContext.request.scheme}" />--%>
<%--<c:set var="serverName" value="${pageContext.request.serverName}" />--%>
<%--<c:set var="serverPort" value="${pageContext.request.serverPort}" />--%>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}" />--%>
<%--<c:set var="url1" value="${scheme}://${serverName}:${serverPort}${contextPath}"/>--%>
<html>
<head>
    <%@ include file="/layouts/common.jsp"%>
    <style>
        .size {
            width: 100px;
            height: 100px;
        }

        .icon {
            color: white;
            width: 40px;
            height: 40px;
        }

        .text {
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .custom-checkbox {
            width: 20px;
            height: 20px;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: #fff;
            border: 2px solid #ddd;
            border-radius: 3px;
            outline: none;
            cursor: pointer;
            position: relative;
        }

        .custom-checkbox:checked {
            border-color: #28a745;
        }

        .custom-checkbox:checked::after {
            content: '✔';
            position: absolute;
            top: 5px;
            left: 8px;
            color: white; !important;
            transform: translate(-50%, -50%);
            font-size: 14px;
        }


    </style>
    <title>Giỏ Hàng</title>
</head>
<body>
<%@ include file="/layouts/header.jsp"%>
<div class="container">
    <h2 class="text-success text">Thông Tin Giỏ Hàng:  </h2>
    <h4 class="text"> <span> </span> </h4>
    <div class="col-md-12">
        <div class="card card-plain">

            <div class="card-body">
                <form action="../customer?action=goConfirmAddress" method="post">
                <div class="table-responsive">

                    <table class="table table-shopping">
                        <tr>
                            <th class="text-center"><input type="checkbox" id="selectAll" class="custom-checkbox"></th>
                            <th class="text-center">Tên Sản Phẩm</th>
                            <th class="text"></th>
                            <th class="text-center">Số Lượng</th>
                            <th class="text-center">Đơn Vị Tính</th>
                            <th class="text-center">Đơn Giá</th>
                            <th class="text-center">Thành Tiền</th>

                        </tr>

                        <c:forEach items="${cartItemDao.selectCartItemsByCartId(cartDao.selectByCustomerId(sessionScope.customer_login.id).id)}" var="p">

                            <tr id="cart-item-${p.id}">
                                <td class="text-center align-middle">
                                    <input type="checkbox" name="selectedProducts" value="${p.id}" class="custom-checkbox" data-cart-id="${p.id}">
                                </td>
                                <td class="text-center align-middle">${p.product.name}</td>
                                <td>
                                    <div class="img-container">
                                        <img class="size" src="${p.product.imageUrl}" alt="">
                                    </div>
                                </td>

                                <td class="text-center align-middle"> <span id="quantity">${p.quantity}</span>
                                    <div class="btn-group">
                                        <button type="button" id="btn-minus" value="${p.id}" data-cart-id="${p.id}"  class="btn btn-info btn-sm">
                                            <a ><i
                                                    class="bi bi-dash icon"></i></a>
                                        </button>
                                        <button type="button" id="btn-plus" class="btn btn-info btn-sm" value="${p.id}" data-cart-id="${p.id}" >
                                            <a ><i
                                                    class="bi bi-plus icon"></i></a>
                                        </button>
                                    </div>
                                </td>
                                <td class="text-center align-middle">${p.product.unit}</td>
                                <c:choose>
                                    <c:when test="${p.product.discount != null}">
                                        <td class="text-center align-middle"><span
                                                class="text-success"> <fmt:formatNumber
                                                value="${p.product.getFinalPrice()}"
                                                type="currency" currencyCode="VND" minFractionDigits="0" />
											</span> <span
                                                style="text-decoration: line-through; padding-left: 5px">
													<fmt:formatNumber value="${p.product.price}"
                                                                      type="currency" currencyCode="VND" minFractionDigits="0" />
											</span></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center align-middle"><fmt:formatNumber value="${p.product.getFinalPrice()}"
                                                                                               type="currency" currencyCode="VND" minFractionDigits="0"/> </td>
                                    </c:otherwise>
                                </c:choose>

                                <td class="text-center align-middle"> <span id="price"><fmt:formatNumber
                                        value="${p.product.getFinalPrice() * p.quantity}"
                                        type="currency" currencyCode="VND" minFractionDigits="0" /></span> </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div style="text-align: right; margin-right: 20px;">
                        <p>
                         Tổng Tiền:   <span id="totalPrice"> 0
    </span>
                        </p>
<%--                        <a href="../customer?selectedProducts=selectedProducts&action=goConfirmAddress">--%>
                            <button type="submit" class="btn btn-success">Thanh Toán</button>
<%--                        </a>--%>
                    </div>
                </div></form>

                </div>

            </div>


        </div>



    </div>
</div>
<script>
    $(document).ready(function() {
        // Lắng nghe sự kiện thay đổi của checkbox "selectedProducts"
        $('input[name="selectedProducts"]').change(function() {
            updateCart($(this));
        });

        // Lắng nghe sự kiện thay đổi của checkbox "selectAll"
        $('#selectAll').change(function() {
            // Lấy danh sách tất cả các checkbox
            var checkboxes = $('input[name="selectedProducts"]');
            var selectAllChecked = $(this).prop('checked');

            // Đặt trạng thái checked của tất cả các checkbox theo trạng thái của checkbox "Chọn tất cả"
            checkboxes.prop('checked', selectAllChecked);
            updateAllCart(selectAllChecked);
        });
        $('#btn-minus').click(function() {
            var cartId = $(this).data('cart-id');
            updateQuantity(cartId, 'minus');
        });

        // Lắng nghe sự kiện click của nút "btn-plus"
        $('#btn-plus').click(function() {
            var cartId = $(this).data('cart-id');
            updateQuantity(cartId, 'plus');
        });
        function updateQuantity(cartId, action) {
            $.ajax({
                url: '../customer?action=updateQuantityOnCart',
                method: 'POST',
                data: {
                    cartId: cartId,
                    update: action
                },
                success: function(response) {
                    if(response.status == 'update'){
                        // Xử lý phản hồi từ server cho việc cập nhật số lượng
                        // Cập nhật giao diện người dùng nếu cần
                        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response.priceUpdate);
                        $('#price').text(formattedPrice);
                        // Cập nhật số lượng hiển thị
                        $('#quantity').text(response.quantity);
                    }else if(response.status == 'delete'){
                        $('#cart-item-' + cartId).remove();
                    }
                },
            });
        }

        // Hàm để cập nhật giỏ hàng
        function updateCart(checkbox) {
            var cartId = checkbox.data('cart-id');
            var isChecked = checkbox.prop('checked');
            $.ajax({
                url: '../customer?action=selectProductOnCart',
                method: 'POST',
                data: {
                    cartId: cartId,
                    isChecked: isChecked
                },
                success: function(response) {
                    // Định dạng giá trị tổng thành tiền và cập nhật vào thẻ có id là "totalPrice"
                    var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response);
                    $('#totalPrice').text(formattedPrice);
                },
            });
        }
        function updateAllCart(isChecked) {
            $.ajax({
                url: '../customer?action=selectAllProductsOnCart',
                method: 'POST',
                data: {
                    isChecked: isChecked
                },
                success: function(response) {
                    // Định dạng giá trị tổng thành tiền và cập nhật vào thẻ có id là "totalPrice"
                    var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response);
                    $('#totalPrice').text(formattedPrice);
                },
            });
        }
    });

</script>
    </body>
</html>

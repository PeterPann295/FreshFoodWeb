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

                            <tr>
                                <td class="text-center align-middle">
                                    <input type="checkbox" name="selectedProducts" value="${p.id}" class="custom-checkbox" data-product-id="${p.product.id}">
                                </td>
                                <td class="text-center align-middle">${p.product.name}</td>
                                <td>
                                    <div class="img-container">
                                        <img class="size" src="${p.product.imageUrl}" alt="">
                                    </div>
                                </td>

                                <td class="text-center align-middle">${p.quantity}
                                    <div class="btn-group">
                                        <button class="btn btn-info btn-sm">
                                            <a href="MinusSanPham?product=${p.product.id}"><i
                                                    class="bi bi-dash icon"></i></a>
                                        </button>
                                        <button class="btn btn-info btn-sm">
                                            <a href="PlusSanPham?product=${p.product.id}"><i
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
                                                type="currency" currencyCode="VND" />
											</span> <span
                                                style="text-decoration: line-through; padding-left: 5px">
													<fmt:formatNumber value="${p.product.price}"
                                                                      type="currency" currencyCode="VND" />
											</span></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center align-middle"><fmt:formatNumber value="${p.product.getFinalPrice()}"
                                                                                               type="currency" currencyCode="VND" /> </td>
                                    </c:otherwise>
                                </c:choose>

                                <td class="text-center align-middle"><fmt:formatNumber
                                        value="${p.product.getFinalPrice() * p.quantity}"
                                        type="currency" currencyCode="VND" /></td>
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
                </form>
                </div>

            </div>


        </div>



    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Lắng nghe sự kiện click trên checkbox "Chọn tất cả"
        document.getElementById('selectAll').addEventListener('change', function() {
            // Lấy danh sách tất cả các checkbox
            var checkboxes = document.querySelectorAll('input[name="selectedProducts"]');

            // Đặt trạng thái checked của tất cả các checkbox theo trạng thái của checkbox "Chọn tất cả"
            checkboxes.forEach(function(checkbox) {
                checkbox.checked = document.getElementById('selectAll').checked;
            });
        });
    });
    $(document).ready(function() {
        // Lắng nghe sự kiện thay đổi của checkbox
        $('input[name="selectedProducts"]').change(function() {
            // Lấy ID sản phẩm từ thuộc tính data-product-id
            var productId = $(this).data('product-id');
            // Lấy trạng thái checked của checkbox
            var isChecked = $(this).prop('checked');

            // Gửi yêu cầu Ajax
            $.ajax({
                url: '../customer?action=selectProductOnCart',
                method: 'POST',
                data: {
                    productId: productId,
                    isChecked: isChecked

                },
                success: function(response) {
                    var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response);
                    // Gán giá trị đã định dạng vào thẻ có id là "totalPrice"
                    $('#totalPrice').text(formattedPrice);
                },

            });
        });
    });
</script>
</body>
</html>

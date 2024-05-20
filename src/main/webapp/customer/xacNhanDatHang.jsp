<%--
  Created by IntelliJ IDEA.
  User: lemin
  Date: 5/19/2024
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
    <link rel="stylesheet" type="text/css" href="/assets/css/dangKi.css">
    <style>
        .btn-check-voucher {
            border: none;
            background: #ee4d2d;
            color: white;
            border-radius: 5px
        }
    </style>
</head>
<body>
<%@ include file="/layouts/header.jsp"%>

<div class="container_form">
    <form class="form-container" action="../customer?action=order" method="post">
        <table class="table">

            <tr>
                <th colspan="2" style="text-align: center;"> <h3 class="text-success">Xác Nhận Mua Hàng</h3> </th>
            </tr>
            <tr>
                <td>Tên Người Nhận Hàng: </td>
                <td> ${order.toName} </td>
            </tr>
            <tr>
                <td> Số Điện Thoại: </td>
                <td> ${order.numberPhone} </td>
            </tr>
            <tr>
                <td> Địa chỉ nhận hàng: </td>
                <td> ${order.to_address} </td>
            </tr>
            <tr>
                <td> Sản Phẩm: </td>
                <td> <c:forEach var="p" items="${order.orderItems}">
                    <p> <img
                            style="width: 40px; height: 40px" src="${p.product.imageUrl}" alt="Logo"
                    > <b>${p.product.name}</b>  : x${p.quantity} </p>
                </c:forEach>
                </td>
            </tr>
            <tr>
                <td> Lưu ý: </td>
                <td class="voucher-input">
                    <input type="text"  name="note" placeholder="Lưu ý cho người bán">
                </td>
            </tr>

            <tr>
                <td> Ngày giao hàng dự kiến: </td>
                <td> <fmt:formatDate value="${order.deliveryDate}" pattern="dd/MM/yyyy" />
                </td>
            </tr>
            <tr>
                <td>Nhập mã voucher:</td>
                <td class="voucher-input">
                    <input type="text" id="voucher_code" name="voucher_code" placeholder="Nhập mã voucher">
                    <button class="btn-check-voucher" type="button" onclick="validateVoucher()">Xác nhận mã</button>
                </td>
            </tr>
            <tr>
                <td style="border: none"></td>
                <td style="border: none">                    <span id="incorrect" style="color: red" class="tr"></span>
                </td>
            </tr>
            <tr>
                <td> <h6> Tổng tiền sản phẩm: </h6> </td>
                <td> <b><fmt:formatNumber value="${order.totalPriceProduct()}" type="currency"
                                          currencyCode="VND" minFractionDigits="0" /></b> </td>
            </tr>
            <tr>
                <td> <h6> Phí vận chuyển: </h6> </td>
                <td> <b><fmt:formatNumber value="${order.deliveryFee}" type="currency"
                                          currencyCode="VND" minFractionDigits="0" /></b> </td>
            </tr>
            <tr id="voucherDiscount" style="display: none">
                <td> <h6>Số tiền giảm giá: </h6> </td>
                <td id="discount" style="font-weight: bolder"></td>
            </tr>
            <tr>
                <td> <h5> Tổng tiền thanh toán: </h5> </td>
                <td id="totalPrice" style="font-weight: bolder"> <fmt:formatNumber value="${order.total}" type="currency"
                                          currencyCode="VND" minFractionDigits="0" /> </td>
            </tr>
            <tr>
                <td>Phương thức thanh toán: </td>
                <td>
                    <input class="form-check-input" style="margin-bottom: 10px" type="radio" id="cash_on_delivery" name="payment_method" value="1">
                    <label for="cash_on_delivery">Thanh toán khi nhận hàng</label><br>
                    <input class="form-check-input" type="radio" id="bank_transfer" name="payment_method" value="2">
                    <label for="bank_transfer">Thanh toán qua ngân hàng</label>
                </td>
            </tr>


        </table>
        <button type="submit" class="btn btn-primary" style="width: 100%;">Xác Nhận Đặt Hàng</button>
    </form>
</div>
<script>
    function validateVoucher() {
        var voucherCode = $('#voucher_code').val();
        if (voucherCode) {
            $.ajax({
                type: "POST",
                url: "../customer?action=confirmVoucher", // Thay thế bằng URL của servlet của bạn
                data: { voucherCode: voucherCode },
                success: function(response) {
                    if(response.status == 'incorrect'){
                        console.log("da vao day")
                        $('#incorrect').text("Voucher không hợp lệ");
                        $('#voucherDiscount').css('display', 'none');
                        var totalPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response.totalPrice);
                        $('#totalPrice').text(totalPrice);
                    }else if(response.status == 'correct'){
                        $('#incorrect').text("");
                        var totalPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response.totalPrice);
                        var discount = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(response.discount);
                        $('#voucherDiscount').css('display', 'table-row');
                        $('#totalPrice').text(totalPrice);
                        $('#discount').text(discount);
                    }
                },
                error: function(xhr, status, error) {
                    alert("Đã xảy ra lỗi: " + error);
                }
            });
        } else {
            alert("Vui lòng nhập mã voucher.");
        }
    }
</script>
<%@ include file="/layouts/footer.jsp"%>
</body>
</html>

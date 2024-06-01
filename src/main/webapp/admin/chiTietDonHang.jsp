<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="layouts/svg.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh mục lớn</title>
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
            <div class="container_form" style="margin-top: 60px;">
                <form class="form-container" action="/admin/donHang.jsp">
                    <table class="table">

                        <tr>
                            <th colspan="2" style="text-align: center;"> <h3 class="text-success">Chi Tiết Đơn Hàng  </h3> </th>
                        </tr>
                        <tr>
                            <td> Người Đặt Hàng (id): </td>
                            <td> ${orderDetail.customer.id} </td>
                        </tr>
                        <tr>
                            <td> Người Đặt Hàng : </td>
                            <td> ${orderDetail.customer.fullName} </td>
                        </tr>
                        <tr>
                            <td>Tên Người Nhận Hàng: </td>
                            <td> ${orderDetail.toName} </td>
                        </tr>
                        <tr>
                            <td> Số Điện Thoại: </td>
                            <td> ${orderDetail.numberPhone} </td>
                        </tr>
                        <tr>
                            <td> Địa chỉ nhận hàng: </td>
                            <td> ${orderDetail.to_address} </td>
                        </tr>
                        <tr>
                            <td> Sản Phẩm: </td>
                            <td> <c:forEach var="p" items="${orderDetail.orderItems}">
                                <p> <img
                                        style="width: 40px; height: 40px" src="${p.product.imageUrl}" alt="Logo"
                                > <b>${p.product.name}</b>  : x${p.quantity} </p>
                            </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td> Lưu ý: </td>
                            <td> ${orderDetail.note} </td>
                        </tr>
                        <tr>
                            <td> Tình Trạng Đơn Hàng: </td>
                            <b>                <td> ${orderDetail.status.name} </td>
                            </b>
                        </tr>
                        <tr>
                            <td> Ngày đặt hàng: </td>
                            <td> <fmt:formatDate value="${orderDetail.date}" pattern="dd/MM/yyyy" />
                            </td>
                        </tr>
                        <tr>
                            <td> Ngày giao hàng dự kiến: </td>
                            <td> <fmt:formatDate value="${orderDetail.deliveryDate}" pattern="dd/MM/yyyy" />
                            </td>
                        </tr>
                        <tr>
                            <td>Mã voucher:</td>
                            <td> ${orderDetail.voucher.code} </td>
                        </tr>

                        <tr>
                            <td> <h6> Tổng tiền sản phẩm: </h6> </td>
                            <td> <b><fmt:formatNumber value="${orderDetail.priceProduct()}" type="currency"
                                                      currencyCode="VND" minFractionDigits="0" /></b> </td>
                        </tr>
                        <tr>
                            <td> <h6> Phí vận chuyển: </h6> </td>
                            <td> <b><fmt:formatNumber value="${orderDetail.deliveryFee}" type="currency"
                                                      currencyCode="VND" minFractionDigits="0" /></b> </td>
                        </tr>
                        <tr>
                            <td> <h6>Số tiền giảm giá: </h6> </td>
                            <td><c:if test="${orderDetail.voucher == null}">
                                0đ
                            </c:if>
                                <c:if test="${orderDetail.voucher != null}">
                                    <fmt:formatNumber value="${orderDetail.voucher.discount}" type="currency"
                                                      currencyCode="VND" minFractionDigits="0" />
                                </c:if>

                            </td>
                        </tr>
                        <tr>
                            <td> <h5> Tổng tiền thanh toán: </h5> </td>
                            <td id="totalPrice" style="font-weight: bolder"> <fmt:formatNumber value="${orderDetail.total}" type="currency"
                                                                                               currencyCode="VND" minFractionDigits="0" /> </td>
                        </tr>
                        <tr>
                            <td>Phương thức thanh toán: </td>
                            <td>
                                ${orderDetail.paymentMethod.name}
                            </td>
                        </tr>


                    </table>
                    <button type="submit" class="btn btn-primary" style="width: 100%;"> Quay về trang lịch sử mua hàng  </button>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>
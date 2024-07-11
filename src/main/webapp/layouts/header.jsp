<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<jsp:useBean id="cartSize" class="database.CartDao" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="common.jsp"%>
    <style>
        body {
            padding-top: 90px;
            margin: 0; /* Đảm bảo không có margin bổ sung trên body */
        }

        header {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #ffffff; /* Đặt màu nền của header */
            /* Các thuộc tính CSS khác cho header */
        }
    </style>


</head>
<body>
<header class="fixed-top p-3 text-bg-success" style="padding-top: 100px;">
    <div class="container">
        <div
                class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <div>

                <a href="<c:url value='../trangChu.jsp'/>" class=" logo-link"> <img
                        style="width: 60px; height: 50px" src="/assets/images/logo.png" alt="Logo"
                        class="logo-image">
                </a>
            </div>

            <ul
                    class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<c:url value='../trangChu.jsp'/>" class="nav-link px-2 text-white">Trang
                    Chủ</a></li>
                <li><a href="../customer?action=goListProduct&index=1" class="nav-link px-2 text-white">Sản
                    Phẩm</a></li>
                <li><a href="#" class="nav-link px-2 text-white">Giới
                    Thiệu</a></li>
                <li><a href="#" class="nav-link px-2 text-white">Liên Hệ</a></li>
            </ul>

            <form action="../customer?action=searchByNameProduct" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search" method="post">

                <input type="search"
                       class="form-control form-control-dark text-bg-white"
                       placeholder="Tên thực phẩm..." aria-label="Search" name="nameProduct">
            </form>


            <a class="me-2" href="<c:url value='../customer/gioHang.jsp'/>">
                <button class="btn btn-success">
                    <i class="bi bi-cart3"></i> Giỏ Hàng <span id="cart-size"
                                                               class="badge bg-danger cart-size"> <c:if test="${customer_login==null}" >0</c:if>
                <c:if test="${customer_login != null}" > ${cartSize.selectByCustomerId(customer_login.id) == null ? 0 : cartSize.selectByCustomerId(customer_login.id).totalQuantity} </c:if>
                </span>
                </button>
            </a>

            <div class="text-end">


                <c:choose>
                    <c:when test="${sessionScope.customer_login != null}">
                        <div class="flex-shrink-0 dropdown">
                            <a href="#"
                               class="d-block link-body-emphasis text-decoration-none dropdown-toggle text-white"
                               data-bs-toggle="dropdown" aria-expanded="false"> <i
                                    class="bi bi-person" style="font-size: 24px; color: white"></i>

                                Xin Chào ${customer_login.fullName}
                            </a>
                            <ul class="dropdown-menu text-small shadow">
                                <li><a class="dropdown-item" href="<c:url value='../customer/lichSuMuaHang.jsp'/>">Lịch sử mua hàng</a></li>
                                <c:if test="${sessionScope.customer_login.provider == null}">
                                    <li><a class="dropdown-item" href="<c:url value='../customer/thayDoiMatKhau.jsp'/>">Thay đổi mật khẩu</a></li>
                                    <li><a class="dropdown-item" href="<c:url value='../customer/thayDoiThongTin.jsp'/>">Thay đổi thông tin</a></li>
                                </c:if>
                                <li><hr class="dropdown-divider"></li>
                                <c:if test="${sessionScope.customer_login.role == true}">
                                    <li><a class="dropdown-item" href="<c:url value='../admin/thongKe.jsp'/>">Trang quản trị</a></li>

                                </c:if>
                                <li><a class="dropdown-item" href="../customer?action=logout">Đăng xuất</a></li>
                            </ul>

                        </div>


                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='../dangNhap.jsp'/>">
                            <button type="button" class="btn btn-outline-light me-2">
                                Đăng Nhập</button>
                        </a>
                        <a href="<c:url value='../dangKi.jsp'/>">
                            <button type="button" class="btn btn-warning btn-outline-light">
                                Đăng Kí</button>
                        </a>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</header>
</body>
</html>
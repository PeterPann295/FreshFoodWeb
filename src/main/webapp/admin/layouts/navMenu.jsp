
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<div
        class="sidebar border border-right col-md-3 col-lg-2 p-0 bg-body-tertiary">
    <div class="offcanvas-md offcanvas-end bg-body-tertiary" tabindex="-1"
         id="sidebarMenu" aria-labelledby="sidebarMenuLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="sidebarMenuLabel">Fresh Food</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                    data-bs-target="#sidebarMenu" aria-label="Close"></button>
        </div>
        <div
                class="offcanvas-body d-md-flex flex-column p-0 pt-lg-3 overflow-y-auto">
            <ul class="nav flex-column">
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2 active"
                        aria-current="page" href="<c:url value='../admin/thongKe.jsp'/>"> <svg class="bi">
                    <use xlink:href="#graph-up" /></svg> Thống Kê
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/donHang.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark" /></svg> Đơn Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/sanPham.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#cart" /></svg> Sản Phẩm
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/danhMucCha.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#category" /></svg> Danh Mục Lớn
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/danhMucCha.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#category" /></svg> Danh Mục
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/admin.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#admin" /></svg> Admin
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/khachHang.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#people" /></svg> Khách Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/nhapSanPham.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#import" /></svg> Nhập Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/voucher.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#voucher" /></svg> Quản lí voucher
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/discount.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#discount" /></svg> Quản lí discount
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/quanLyLog.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#log" /></svg> Quản Lí Log
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="<c:url value='../admin/lienHeAdmin.jsp'/>"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Phản hồi từ khách hàng
                </a></li>

            </ul>

            <h6
                    class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-body-secondary text-uppercase">
                <span>Saved reports</span> <a class="link-secondary" href="#"
                                              aria-label="Add a new report"> <svg class="bi">
                <use xlink:href="#plus-circle" /></svg>
            </a>
            </h6>
            <ul class="nav flex-column mb-auto">
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="xuHuongSanPham.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Xu Hướng Bán Hàng Của Sản Phẩm 3 Tháng Gần Nhất
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="xuHuongDatHang.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Xu Hướng Đặt Hàng Của Khách Hàng Trong 3 Tháng Gần Nhất
                </a></li>


            </ul>

            <hr class="my-3">

            <ul class="nav flex-column mb-auto">
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="../trangChu.jsp"> <svg
                        class="bi">
                    <use xlink:href="#gear-wide-connected" /></svg> Trang Mua Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="../customer?action=logout"> <svg
                        class="bi">
                    <use xlink:href="#door-closed" /></svg> Đăng Xuất
                </a></li>
            </ul>
        </div>
    </div>
</div>


</body>
</html>

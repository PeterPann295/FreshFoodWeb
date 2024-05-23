
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
                        aria-current="page" href="dashBoard.jsp"> <svg class="bi">
                    <use xlink:href="#graph-up" /></svg> Thống Kê
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="hoadonadmin"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark" /></svg> Hóa Đơn
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="sanPhamAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#cart" /></svg> Sản Phẩm
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="khachHangAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#people" /></svg> Khách Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="nguoiDungLaAdminAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#people" /></svg> Admin
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
                        class="nav-link d-flex align-items-center gap-2" href="danhMucLonAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Danh Mục Lớn
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="danhMucAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Danh Mục
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="chuongTrinhKhuyenMaiAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Chương Trình Giảm Giá
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="top5SanPhamBanChayAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Top 5 Sản Phẩm Bán Chạy Nhất
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="top5KhachHangAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Top 5 Khách Hàng Mua Nhiều Nhất
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="lienHeAdmin.jsp"> <svg
                        class="bi">
                    <use xlink:href="#file-earmark-text" /></svg> Phản Hồi Từ Khách Hàng
                </a></li>
            </ul>

            <hr class="my-3">

            <ul class="nav flex-column mb-auto">
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="trangChu.jsp"> <svg
                        class="bi">
                    <use xlink:href="#gear-wide-connected" /></svg> Trang Mua Hàng
                </a></li>
                <li class="nav-item"><a
                        class="nav-link d-flex align-items-center gap-2" href="dangxuat"> <svg
                        class="bi">
                    <use xlink:href="#door-closed" /></svg> Đăng Xuất
                </a></li>
            </ul>
        </div>
    </div>
</div>


</body>
</html>

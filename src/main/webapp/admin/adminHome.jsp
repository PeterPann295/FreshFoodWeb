<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Retrieve session attributes with proper null and type checks
    int countOrder = session.getAttribute("countOrder") != null ? Integer.parseInt(session.getAttribute("countOrder").toString()) : 0;
    int countProductOutStock = session.getAttribute("countProductOutStock") != null ? Integer.parseInt(session.getAttribute("countProductOutStock").toString()) : 0;
    int totalMoney = session.getAttribute("totalMoney") != null ? Integer.parseInt(session.getAttribute("totalMoney").toString()) : 0;
    int countOrderCancel = session.getAttribute("countOrderCancel") != null ? Integer.parseInt(session.getAttribute("countOrderCancel").toString()) : 0;

    // Retrieve lists from session
    List<Order> listOrderRecent = session.getAttribute("listOrderRecent") != null ? (List<Order>) session.getAttribute("listOrderRecent") : new ArrayList<>();
    List<Product> listProductBestSeller = session.getAttribute("listProductBestSeller") != null ? (List<Product>) session.getAttribute("listProductBestSeller") : new ArrayList<>();

    // Format currency in Vietnamese locale
    Locale locale = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getInstance(locale);
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<style>
    .home {
        display: grid;
        grid-template-columns: 0.5fr  2.5fr;
        gap: 50px;
        height: 90vh;
    }
    .adminHeader {
        width: 100%;
    }

    .home-section {
        width: 100%;
        border: 1px solid #4F6F52;
        border-radius: 10px;
        padding: 10px;
        margin: 0 20px 5px 5px;
        max-width: 95%;
    }

    .view-box {
        width: 100%;
    }

    .header-box {
        margin-bottom: 20px;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
    }

    #buttonGroup {
        margin-top: 10px;
    }

    #buttonGroup a {
        margin-right: 10px;
        color: #2ecc71;
        text-decoration: none;
    }

    #buttonGroup a:hover {
        text-decoration: underline;
    }

    .overview-boxes {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
    }

    .box {
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        flex: 1;
        margin-right: 10px;
    }

    .box:last-child {
        margin-right: 0;
    }

    .box-topic {
        font-size: 18px;
        margin-bottom: 10px;
    }

    .number {
        font-size: 24px;
        font-weight: bold;
    }

    .sales-boxes {
        display: flex;
        justify-content: space-between;
    }

    .recent-sales, .top-sales {
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        width: 30%;
    }

    .recent-sales .title, .top-sales .title {
        font-size: 20px;
        margin-bottom: 20px;
    }

    .recent-sales table, .top-sales ul {
        width: 100%;
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .recent-sales table th, .recent-sales table td, .top-sales ul li {
        padding: 10px 0;
    }

    .recent-sales table th, .recent-sales table td {
        border-bottom: 1px solid #eeeeee;
    }

    .recent-sales .button {
        text-align: right;
        margin-top: 20px;
    }

    .recent-sales .button a {
        color: #2ecc71;
        text-decoration: none;
    }

    .recent-sales .button a:hover {
        text-decoration: underline;
    }

    .top-sales ul li {
        display: flex;
        justify-content: space-between;
    }

    .product {
        font-size: 16px;
    }

    .price {
        font-weight: bold;
    }

    @media screen and (max-width: 768px) {
        .home-section {
            flex-direction: row;
        }

        .view-box {
            width: 100%;
            margin-right: 0;
            margin-bottom: 20px;
        }

        .sales-boxes {
            flex-direction: column;
        }

        .recent-sales, .top-sales {
            width: 100%;
            margin-bottom: 20px;
        }
    }

</style>
<body>
<div class="home">
    <div class="adminHeader">
        <%@ include file="adminHeader.jsp" %>
    </div>

<section class="home-section">
        <div class="view-box">
            <div class="header-box">
                <div class="title">Thống kê</div>
                <div id="buttonGroup">
                    <a href="pageAdminController">Tất cả</a>
                    <a href="pageAdminControllerToday">Hôm nay</a>
                    <a href="pageAdminControllerWeek">Tuần</a>
                    <a href="pageAdminControllerMonth">Tháng</a>
                </div>
            </div>
            <div class="overview-boxes">
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Tổng Đơn Hàng</div>
                        <div class="number"><h3><%= countOrder %></h3></div>
                    </div>
                    <i class="fa-solid fa-cart-plus cart"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Hết Hàng</div>
                        <div class="number"><h3><%= countProductOutStock %></h3></div>
                    </div>
                    <i class="fa-solid fa-xmark cart two"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Doanh Thu</div>
                        <div class="number"><h3><%= numberFormat.format(totalMoney) %> vnđ</h3></div>
                    </div>
                    <i class="fa-solid fa-dollar-sign cart three"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Đơn Hàng Hủy</div>
                        <div class="number"><h3><%= countOrderCancel %></h3></div>
                    </div>
                    <i class="fa-solid fa-scroll cart four"></i>
                </div>
            </div>
        </div>
        <div class="sales-boxes">
            <div class="recent-sales box">
                <div class="title">Đơn Hàng Gần Đây</div>
                <div class="sales-details">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã đơn hàng</th>
                            <th scope="col">Địa chỉ</th>
                            <th scope="col">Ngày đặt</th>
                            <th scope="col">Số điện thoại</th>
                            <th scope="col">Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (Order order : listOrderRecent) { %>
                        <tr>
                            <th scope="row"><%= order.getId() %></th>
                            <td><%= order.getTo_address() %></td>
                            <td><%= order.getDate() %></td>
                            <td><%= order.getNumberPhone() %></td>
                            <td><%= numberFormat.format(order.getTotal()) %> đ</td>
                        </tr>
                        <% } %>

                        </tbody>
                    </table>
                </div>
                <div class="button">
                    <a href="adminCheck">Xem tất cả</a>
                </div>
            </div>
            <div class="top-sales box">
                <div class="title">Sản Phẩm Bán Chạy</div>
                <ul class="top-sales-details">
                    <% for (Product product : listProductBestSeller) { %>
                    <li>
                        <a href="#">
                            <span class="product" title="<%= product.getName() %>"><%= product.getName() %></span>
                            <span class="price"><%= numberFormat.format(product.getFinalPrice()) %> đ</span>
                        </a>
                    </li>
                    <% } %>

                </ul>
            </div>
        </div>
</section>
</div>
</body>
</html>

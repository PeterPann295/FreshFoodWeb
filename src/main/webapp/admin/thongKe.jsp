<%--
  Created by IntelliJ IDEA.
  User: lemin
  Date: 6/3/2024
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="order" class="database.OrderDao" />
<html>
<head>
    <title>Title</title>
    <jsp:include page="../layouts/common.jsp" />
</head>
<body>
<%@ include file="layouts/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 >Tổng doanh thu cửa hàng: <fmt:formatNumber value="${order.totalRevenue()}"
                                                                type="currency" currencyCode="VND" minFractionDigits="0"  /></h3>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group me-2">
                        <a href="thongKeTheoNam.jsp"><button type="button" class="btn btn-sm btn-outline-success">Theo Năm</button> </a>
                        <a href="thongKe.jsp"><button type="button" class="btn btn-sm btn-outline-secondary">7 Ngày Gần Nhất</button></a>
                    </div>

                </div>
            </div>
            <h5 class="text-success" style="margin-left: 40px;">Biểu Đồ Doanh Thu 7 Ngày Bán Hàng Gần Nhất</h5>

            <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
            <div class="table-responsive small">
                <table class="table table-striped table-sm">

                </table>
            </div>
        </main>
    </div>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/chart.js@4.3.2/dist/chart.umd.js"
        integrity="sha384-eI7PSr3L1XLISH8JdDII5YN/njoSsxfbrkCTnJrzXt+ENP5MOVBxD+l6sEG4zoLp"
        crossorigin="anonymous"></script>

</body>
<script>
    (() => {
        'use strict';

        // Lấy tham chiếu đến canvas
        const ctx = document.getElementById('myChart');

        // Sử dụng Fetch API để lấy dữ liệu từ servlet
        fetch('../admin?action=getTotalRevenue7Days') // Thay thế '/yourContextPath' bằng đường dẫn thực tế của bạn
            .then(response => response.json())
            .then(data => {
                console.log(data)
                const labels = [];
                const dataPoints = [];

                // Lặp qua mỗi đối tượng trong mảng phản hồi
                data.forEach(item => {
                    // Trích xuất orderDate và totalAmount từ mỗi đối tượng
                    const orderDate = item.orderDate;
                    const totalAmount = item.totalAmount;

                    // Thêm orderDate vào mảng labels
                    labels.push(orderDate);
                    // Thêm totalAmount vào mảng dataPoints
                    dataPoints.push(totalAmount);
                });
                // Vẽ biểu đồ khi dữ liệu đã được nhận được
                const myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels, // Parse chuỗi JSON thành mảng nhãn
                        datasets: [{
                            data: dataPoints, // Parse chuỗi JSON thành mảng dữ liệu
                            lineTension: 0,
                            backgroundColor: 'transparent',
                            borderColor: '#007bff',
                            borderWidth: 4,
                            pointBackgroundColor: '#007bff'
                        }]
                    },
                    options: {
                        plugins: {
                            legend: {
                                display: false
                            },
                            tooltip: {
                                boxPadding: 3
                            }
                        }
                    }
                });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    })();
</script>
</html>

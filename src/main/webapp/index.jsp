<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/11/2024
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <%@ include file="layouts/common.jsp"%>
</head>

<body>
<div>
    <%@ include file="layouts/header.jsp"%>
    <div id="myCarousel" class="carousel slide mb-6"
         data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" style="background-color: green"
                    data-bs-target="#myCarousel" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img alt="" src="assets/images/fresh.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption text-start">
                        <h1>Example headline.</h1>
                        <p class="opacity-75 text-success"
                           style="font-size: 33px; margin-bottom: 50px;">
                            "Hãy yêu thương bản thân <br> mình, bằng những sản <br>
                            phẩm chất lượng."
                        </p>
                        <p style="margin-bottom: 80px;">
                            <a class="btn btn-lg btn-primary" href="dangNhap.jsp">Đăng nhập ngay!</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img alt="" src="assets/images/slide2.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption">
                        <p style="margin-right: 50px; margin-bottom: 80px;">
                            <a class="btn btn-lg btn-primary" href="sanPham.jsp">Xem Ngay</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img alt="" src="assets/images/food.png" style="width: 100%; height: 100%">
                <div class="container">
                    <div class="carousel-caption text-end"
                         style="margin-bottom: 80px; margin-right: 45px;">
                        <p style="margin-right: 20px">
                            <a class="btn btn-lg btn-primary" href="sanPham.jsp">Xem Ngay</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button"
                data-bs-target="#myCarousel" data-bs-slide="prev">
				<span class="carousel-control-prev-icon"
                      style="background-color: green" aria-hidden="true"></span> <span
                class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button"
                data-bs-target="#myCarousel" data-bs-slide="next">
				<span class="carousel-control-next-icon"
                      style="background-color: green" aria-hidden="true"></span> <span
                class="visually-hidden">Next</span>
        </button>


    </div>
    <hr>
</div>

</body>
</html>

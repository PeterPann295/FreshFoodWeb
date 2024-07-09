
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="../../layouts/common.jsp"%>
    <link href="../assets/css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="svg.jsp"%>

<header class="navbar sticky-top bg-success flex-md-nowrap p-0 shadow"
        data-bs-theme="dark">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 text-white bg-success "
       href="dashBoard.jsp">Fresh Food <img
            style="width: 40px; height: 40px" src="../assets/images/logo.png" alt="Logo"
            class="logo-image"> </a>

    <ul class="navbar-nav flex-row d-md-none">
        <li class="nav-item text-nowrap">
            <button class="nav-link px-3 text-white" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarSearch"
                    aria-controls="navbarSearch" aria-expanded="false"
                    aria-label="Toggle search">
                <svg class="bi">
                    <use xlink:href="#search" /></svg>
            </button>
        </li>
        <li class="nav-item text-nowrap">
            <button class="nav-link px-3 text-white" type="button"
                    data-bs-toggle="offcanvas" data-bs-target="#sidebarMenu"
                    aria-controls="sidebarMenu" aria-expanded="false"
                    aria-label="Toggle navigation">
                <svg class="bi">
                    <use xlink:href="#list" /></svg>
            </button>
        </li>
    </ul>

    <div class="text-end text-white me-5"> Xin chào  ${sessionScope.customer_login.username} </div>

    <div id="navbarSearch" class="navbar-search w-100 collapse">
        <input class="form-control w-100 rounded-0 border-0" type="text"
               placeholder="Search" aria-label="Search">
    </div>
</header>
</body>
</html>

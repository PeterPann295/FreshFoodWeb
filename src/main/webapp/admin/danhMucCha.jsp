<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="parentCategoryDAO" class="database.ParentCategoryDao" />
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <link href="assets/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Danh Mục Lớn</title>
    <style>
        td, th {
            vertical-align: middle; /* Căn giữa theo chiều dọc */
        }
    </style>
</head>
<body>

<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h2">Danh Sách Danh Mục Cha</h3>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="themDanhMucCha.jsp">
                        <button type="button" class="btn btn-sm btn-outline-success">
                            Thêm Danh Mục Cha</button>
                    </a>
                </div>
            </div>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col" ></th>
                    <th scope="col">Tên Danh Mục Cha</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${parentCategoryDAO.selectAll()}">
                <tr>
                    <th scope="row"> ${p.id}  </th>
                    <td> <img
                            style="width: 60px; height: 50px" src="${p.imageURL}" alt="Logo"
                            class="logo-image"> </td>
                    <td> ${p.name} </td>

                    </c:forEach>

                </tbody>
            </table>

        </main>
    </div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ include file="layouts/svg.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="discountDAO" class="database.DiscountDao"
/>
<jsp:useBean id="categoryDAO" class="database.CategoryDao"
/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
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
            <div class="container_form">
                <form class="form-container" action="quanLyLog.jsp"
                      >
                    <h3 class="text-center text-success">Chi tiết Log #${log.id}</h3>

                    <table class="table">
                        <tr>
                            <td>Level</td>
                            <td>   <c:if test="${log.level eq 'infor'}" >
                                <div class="badge text-bg-success text-wrap" style="width: 6rem;">
                                    Infor
                                </div>
                                </c:if>
                                <c:if test="${log.level eq 'warning'}" >
                                <div class="badge text-bg-warning text-wrap" style="width: 6rem;">
                                    Warning
                                </div>
                                </c:if>
                                <c:if test="${log.level eq 'danger'}" >
                                <div class="badge text-bg-danger text-wrap" style="width: 6rem;">
                                    Danger
                                </div>
                                </c:if> </td>

                        </tr>

                        <tr>
                            <td>#ID</td>
                            <td>${log.id}</td>
                        </tr>
                        <tr>
                            <td>Action</td>
                            <td>${log.action}</td>
                        </tr>
                        <tr>
                            <td>Table</td>
                            <td>${log.table}</td>
                        </tr>
                        <tr>
                            <td>UserId</td>
                            <td>${log.user_id}</td>
                        </tr>
                        <tr>
                            <td>Thời gian</td>
                            <td> <fmt:formatDate value="${log.time}" pattern="dd/MM/yyyy hh:MM:ss" />
                        </tr>

                    </table>
                    <div class="mb-3">
                        <label for="before" class="form-label">Before Data </label>
                        <textarea type="text" class="form-control" id="before"
                                  placeholder="" rows="5"
                                  cols="50" readonly> ${log.beforeData} </textarea>

                    </div>
                    <div class="mb-3">
                        <label for="after" class="form-label"> After Data </label>
                        <textarea type="text" class="form-control" id="after"
                                   placeholder="" rows="5"
                                  cols="50" readonly> ${log.afterData} </textarea>

                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary width-btn">Quay về quản lý log</button>
                    </div>

                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>
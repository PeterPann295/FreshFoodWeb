<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phản hồi</title>
    <script src="javascript/script.js"></script>
</head>
<body>
<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="container_form">
                <form class="form-container" action="${pageContext.request.contextPath}/admin?action=sendMail&contactID=${contact.contactId}" method="post">

                <table class="table">
                        <tr>
                            <th colspan="2" style="text-align: center;">
                                <h3 class="text-success">Phản hồi khách hàng</h3>
                            </th>
                        </tr>
                        <tr>
                            <td>Mã phản hồi</td>
                            <td>${contact.contactId}</td>
                        </tr>
                        <tr>
                            <td>Tên khách hàng</td>
                            <td>${contact.name}</td>
                        </tr>
                        <tr>
                            <td>Số điện thoại</td>
                            <td>${contact.numberPhone}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${contact.email}</td>
                        </tr>
                        <tr>
                            <td>Nội dung</td>
                            <td>${contact.content}</td>
                        </tr>
                    </table>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="tieuDe" name="tieuDe" placeholder="Tiêu đề" required>
                    </div>
                    <div class="mb-3">
                        <textarea class="form-control" id="noiDung" name="noiDung" rows="3" placeholder="Nội dung phản hồi" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">Phản hồi</button>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>

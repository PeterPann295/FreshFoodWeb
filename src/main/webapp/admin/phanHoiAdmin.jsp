<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phản hồi</title>
    <script src="javascript/script.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styleDangKi.css">
</head>
<body>
<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div class="container_form">
                <form class="form-container" action="lienhe">
                    <input type="hidden" name="hanhDong" value="guiMail"> <input
                        type="hidden" name="contactID" value="${contact.contactId}">

                    <table class="table">

                        <tr>
                            <th colspan="2" style="text-align: center;">
                                <h3 class="text-success">Phản Hồi Khách Hàng</h3>
                            </th>
                        </tr>
                        <tr>
                            <td>Mã Phản Hồi</td>
                            <td>${contact.contactId}</td>
                        </tr>
                        <tr>
                            <td>Tên Khách Hàng</td>
                            <td>${contact.name}</td>
                        </tr>
                        <tr>
                            <td>Số Điện Thoại</td>
                            <td>${contact.numberPhone}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${contact.email}</td>
                        </tr>
                        <tr>
                            <td>Nội Dung</td>
                            <td>${contact.content}</td>
                        </tr>
                    </table>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="full-name"
                               name="tieuDe" placeholder="Tiêu đề" required="required">
                    </div>
                    <div class="mb-3">

							<textarea class="form-control" id="content" name="noiDung"
                                      rows="3" placeholder="Nội dung phản hồi" required="required"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary"
                            style="width: 100%;">Phản Hồi</button>

                </form>
            </div>

        </main>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liên Hệ</title>
    <%@ include file="/layouts/common.jsp"%>
    <style>
        .card:hover {
            border: 2px solid;
            border-color: #28a745;
        }

        .discount-percentage {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: red;
            color: white;
            padding: 5px;
            font-weight: bold;
        }

        .table tr {
            background-color: #efffcf;
            padding: 20px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="css/styleDangKi.css">

</head>
<body>

<%@ include file="/layouts/header.jsp"%>

<div class="row ms-4 mt-4" style="margin-bottom: 30px">

    <div class="col-lg-6  mt-1">
        <h5 class="text-success">${respone}</h5>
        <div class="container_form">
            <form class="form-container" style="background-color: #efffcf">

                <table>

                    <tr>
                        <th colspan="2" style="text-align: center;">
                            <h3 class="text-success">Fresh Food Thực Phẩm Sạch</h3>
                        </th>
                    </tr>
                    <tr>
                        <td><b>Địa Chỉ:</b></td>
                        <td>Linh Trung, TP. Thủ Đức, TP.HCM</td>

                    </tr>
                    <tr>
                        <td><b>Số Điện Thoại:</b></td>
                        <td>0345778312</td>

                    </tr>
                    <tr>
                        <td><b>Email:</b></td>
                        <td>21130354.st@hcmuaf.edu.vn</td>
                    </tr>
                    <tr>
                        <td><b>Mở Cửa:</b></td>
                        <td>8h - 22h, Từ thứ 2 - chủ nhật</td>
                    </tr>

                </table>

            </form>
        </div>
        <div class="container_form">
            <form class="form-container" action="../admin?action=addContact&Contact=${respone}" method="post"
                  style="background-color: #efffcf">
                <input type="hidden" name="action" value="addContact">
                <h3 class="text-success">Liên Hệ Với Chúng Tôi</h3>
                <p>Nếu bạn có thắc mắc gì, có thể gửi yêu cầu cho chúng tôi, và
                    chúng tôi sẽ liên lạc lại với bạn sớm nhất có thể .</p>

                <div class="mb-3">
                    <input type="text" class="form-control" id="full-name" name="name"
                           placeholder="Họ và tên" required="required">
                </div>
                <div class="mb-3">
                    <input type="tel" class="form-control" id="phone" name="phone"
                           placeholder="Số điện thoại" required="required">
                </div>
                <div class="mb-3">
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="Email" required="required">
                </div>
                <div class="mb-3">

						<textarea class="form-control" id="content" name="content"
                                  rows="3" placeholder="Nội dung" required="required"></textarea>
                </div>
                <button type="submit" class="btn btn-success" style="width: 100%;">Gửi
                    Thông Tin</button>
            </form>
        </div>

    </div>

    <div class="col-lg-6">



        <div class="map mt-4">
            <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.2157622266145!2d106.78957711432126!3d10.871187492257226!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175276398969f7b%3A0x9672b7efd0893fc4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBOw7RuZyBMw6JtIFRwLiBI4buTIENow60gTWluaA!5e0!3m2!1svi!2s!4v1630749508032!5m2!1svi!2s"
                    height="500" width="90%" style="border: 0;" allowfullscreen=""
                    aria-hidden="false" tabindex="0"></iframe>
        </div>
    </div>
</div>
<%@ include file="/layouts/footer.jsp"%>
</body>
<script src="javascript/scriptAjax.js"></script>
</html>
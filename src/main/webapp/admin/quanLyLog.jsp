
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="logDao" class="database.LogDao" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <style>
        td, th {
            vertical-align: middle; /* Căn giữa theo chiều dọc */
        }
    </style>
    <jsp:include page="layouts/cssDatatable.jsp" />
</head>
<body>

<%@ include file="layouts/header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="layouts/navMenu.jsp"%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
         <form action="../admin?action=deleteLog" method="post">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h2"> Quản Lí Log </h3>
                <div id="deleteButton" class="btn-toolbar mb-2 mb-md-0" style="margin-right: 50px; display: none">
                        <button type="submit" class="btn btn-sm btn-outline-success">
                            Xóa Log </button>

                </div>
            </div>
            <table class="table" id="tableLog">
                <thead>
                <tr>
                    <th class="text-center"><input type="checkbox" id="selectAll" class="log-checkbox"></th>
                    <th scope="col">Level</th>
                    <th scope="col">#</th>
                    <th scope="col"> Hành Động </th>
                    <th scope="col"> Table </th>
                    <th scope="col"> Thời gian </th>
                    <th scope="col"></th>
                    <th>Customer Id</th>
                    <th>Before Data</th>
                    <th>After Data</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="p" items="${logDao.selectAll()}">
                    <tr id="log-${p.id}">
                        <td class="text-center align-middle">
                            <input type="checkbox" name="selectedLog" value="${p.id}" class="log-checkbox" data-cart-id="${p.id}">
                        </td>
                        <td>
                            <c:if test="${p.level eq 'infor'}" >
                                <div class="badge text-bg-success text-wrap" style="width: 6rem;">
                                    Infor
                                </div>
                            </c:if>
                            <c:if test="${p.level eq 'warning'}" >
                                <div class="badge text-bg-warning text-wrap" style="width: 6rem;">
                                    Warning
                                </div>
                            </c:if>
                            <c:if test="${p.level eq 'danger'}" >
                                <div class="badge text-bg-danger text-wrap" style="width: 6rem;">
                                    Danger
                                </div>
                            </c:if>
                        </td>
                        <td> ${p.id} </td>
                        <td> ${p.action} </td>
                        <td> ${p.table} </td>
                        <td> <fmt:formatDate value="${p.time}" pattern="dd/MM/yyyy hh:MM:ss" />
                        <td>
                        <a href="../admin?action=detailLog&logId=${p.id}">
                            <button type="button" class="btn btn-sm btn-outline-success">
                                Chi tiết </button>
                        </a>
                    </td>
                        <td> ${p.user_id} </td>
                        <td> ${p.beforeData} </td>
                        <td> ${p.afterData} </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
         </form>
        </main>
    </div>
</div>
<jsp:include page="layouts/jsDatatable.jsp" />
<script>
    new DataTable('#tableLog', {
        layout: {
            topStart: {
                buttons: [
                    {
                        extend: 'copy',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'csv',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'excel',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'pdf',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },
                    {
                        extend: 'print',
                        exportOptions: {
                            columns: ':not(:eq(6))' // xuất tất cả các cột trừ cột thứ 6
                        }
                    },

                ]
            }
        },
        columnDefs: [
            { targets: [0,6], orderable: false },
            { "targets": [7,8,9], "visible": false }// Xét ordering cho cột thứ 6 (số thứ tự là 5)
        ]
    });

</script>

<script>
    // Hàm này được gọi khi checkbox "Select All" được nhấn
    function toggleSelectAll() {
        var checkboxes = document.querySelectorAll('.log-checkbox'); // Lấy tất cả các checkbox
        var selectAllCheckbox = document.getElementById('selectAll'); // Lấy checkbox "Select All"

        // Lặp qua tất cả các checkbox và thiết lập trạng thái checked của chúng
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = selectAllCheckbox.checked;
        });
        if (selectAllCheckbox.checked) {
            // Nếu checkbox "Select All" được chọn, hiển thị nút "Xóa Log"
            deleteButton.style.display = 'block';
        } else {
            // Nếu checkbox "Select All" không được chọn, ẩn nút "Xóa Log"
            deleteButton.style.display = 'none';
        }
    }
    function checkIfAnyCheckboxChecked() {
        var checkboxes = document.querySelectorAll('.log-checkbox'); // Lấy tất cả các checkbox
        var deleteButton = document.getElementById('deleteButton'); // Lấy nút "Xóa Log"

        // Kiểm tra xem có ít nhất một checkbox được chọn không
        var atLeastOneChecked = Array.from(checkboxes).some(function(checkbox) {
            return checkbox.checked;
        });

        // Hiển thị hoặc ẩn nút "Xóa Log" tùy thuộc vào kết quả kiểm tra
        if (atLeastOneChecked) {
            deleteButton.style.display = 'block';
        } else {
            deleteButton.style.display = 'none';
        }
    }

    // Gắn sự kiện click cho tất cả các checkbox
    var checkboxes = document.querySelectorAll('.log-checkbox');
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('click', checkIfAnyCheckboxChecked);
    });
    // Gắn sự kiện click cho checkbox "Select All"
    document.getElementById('selectAll').addEventListener('click', toggleSelectAll);

</script>
</body>
</html>

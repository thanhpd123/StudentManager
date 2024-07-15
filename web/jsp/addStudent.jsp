<%-- 
    Document   : add
    Created on : Jan 26, 2024, 10:22:10 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>THÊM HỌC SINH</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="myContainer">
            <h1>Tạo mới học sinh</h1>
            <div class="update">
                <form action="AddStudent" method="post">
                    <div>Họ: <input type="text" name="HoDem"></div>
                    <div>Tên: <input type="text" name="Ten"></div>
                    <div>Ngày sinh: <input type="date" name="NgaySinh"></div>
                    <div>Giới tính:
                        <input type="radio" id="male" name="GioiTinh" checked value="true">
                        <label for="male">Nam</label>
                        <input type="radio" id="female" name="GioiTinh" value="false">
                        <label for="female">Nữ</label></div>
                    <div>Lớp: 
                        <select name="MaLop">
                            <c:forEach items="${dsLop}" var="i">
                                <option value="${i.getMaLop()}">${i.getTenLop()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="addSubmit">
                        <button type="button" onclick="checkInput(this)">+ Tạo mới</button>
                        <input type="reset" value="↺ Đặt lại">
                        <input type="button" value="⏎ Quay lại" onclick="history.back()">
                    </div>
                </form>
            </div>
        </div>
        <script>
            function checkInput(button) {
                var inputs = document.getElementsByTagName("input");
                var isFilled = true;
                for (var i = 0; i < 3; i++) {
                    if (inputs[i].value === "") {
                        isFilled = false;
                        break;
                    }
                }
                if (!isFilled) {
                    alert("Vui lòng nhập đủ thông tin!");
                } else {
                    alert("Tạo mới thành công!");
                    button.type = "submit";
                }
            }
        </script>
    </body>
</html>
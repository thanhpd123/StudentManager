<%-- 
    Document   : update
    Created on : Jan 26, 2024, 9:47:47 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật thông tin</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="myContainer">
            <h1>Cập nhật thông tin học sinh</h1>
            <form class="update" action="UpdateStudent" method="post">
                <div>Mã HS: <input type="text" readonly name="MaHS" value="${hocSinhCanTim.getMaHS()}"></div>
                <div>Họ: <input type="text" name="HoDem" value="${hocSinhCanTim.getHoDem()}"></div>
                <div>Tên: <input type="text" name="Ten" value="${hocSinhCanTim.getTen()}"></div>
                <div>Ngày sinh: <input type="date" name="NgaySinh" value="${hocSinhCanTim.getNgaySinh()}"></div>
                <div>Giới tính: <input type="radio" id="male" name="GioiTinh" value="true" ${hocSinhCanTim.isGioiTinh()==true?"checked":""}>
                    <label for="male">Nam</label>
                    <input type="radio" id="female" name="GioiTinh" value="false" ${hocSinhCanTim.isGioiTinh()==false?"checked":""}>
                    <label for="female">Nữ</label></div>
                <div>Lớp: 
                    <select name="MaLop">
                        <c:forEach items="${dsLop}" var="i">
                            <option value="${i.getMaLop()}"
                                    <c:if test="${i.getMaLop() == (lopCanTim)}">
                                        selected
                                    </c:if>
                                    >${i.getTenLop()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="addSubmit">
                    <button type="button" onclick="checkInput(this)">Cập nhật</button>
                    <input type="reset" value="↺ Đặt lại">
                    <input type="button" value="⏎ Quay lại" onclick="history.back()">
                </div>
            </form>
        </div>
        <script>
            function checkInput(button) {
                var inputs = document.getElementsByTagName("input");
                var isFilled = true;
                for (var i = 0; i < 4; i++) {
                    if (inputs[i].value === "") {
                        isFilled = false;
                        break;
                    }
                }
                if (!isFilled) {
                    alert("Vui lòng nhập đủ thông tin!");
                } else {
                    alert("Cập nhật thành công!");
                    button.type = "submit";
                }
            }
        </script>
    </body>
</html>

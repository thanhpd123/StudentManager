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
            <h1>Cập nhật thông tin giáo viên</h1>
            <form class="update" action="UpdateTeacher" method="post">
                <div>Mã GV: <input type="text" name="MaGV" readonly value="${giaoVienCanTim.getMaGV()}"></div>
                <div>Họ tên: <input type="text" name="TenGV" value="${giaoVienCanTim.getTenGV()}"></div>
                <div>Ngày sinh: <input type="date" name="NgaySinh" value="${giaoVienCanTim.getNgaySinh()}"></div>
                <div>Giới tính: <input type="radio" id="male" name="GioiTinh" value="true" ${giaoVienCanTim.isGioiTinh()==true?"checked":""}>
                    <label for="male">Nam</label>
                    <input type="radio" id="female" name="GioiTinh" value="false" ${giaoVienCanTim.isGioiTinh()==false?"checked":""}>
                    <label for="female">Nữ</label></div>
                <div>Môn giảng dạy: 
                <select name="MaMonHoc">
                    <c:forEach items="${dsMonHoc}" var="i">
                        <option value="${i.getMaMonHoc()}"
                                <c:if test="${i.getMaMonHoc() == (monHocCanTim)}">
                                    selected
                                </c:if>
                                >${i.getTenMonHoc()}</option>
                    </c:forEach>
                </select></div>
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
                for (var i = 0; i < 2; i++) {
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

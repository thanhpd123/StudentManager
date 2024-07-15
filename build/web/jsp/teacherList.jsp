<%-- 
    Document   : studentlist
    Created on : Jan 25, 2024, 10:31:05 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách giáo viên</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
        <script>
            function doDelete(id) {
                if (confirm("Are you sure?")) {
                    window.location = "DeleteTeacher?MaGV=" + id; //URL cộng thêm
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="myContainer">
            <div>
                <h1>Danh sách giáo viên</h1>
            </div>
            <div class="filter">
                <form action="ViewTeachers" method="post">
                    <div>Theo tên: <input type="text" name="giaoVienCanTim" placeholder="Tìm kiếm..." value="${giaoVienCanTim}"></div>
                    <div>Theo bộ môn
                        <select name="monHocCanTim">
                            <option value="all">-Tất cả-</option>
                            <c:forEach items="${dsMonHoc}" var="i">
                                <option value="${i.getMaMonHoc()}" <c:if test="${i.getMaMonHoc().equals(monHocCanTim)}">selected</c:if>>${i.getTenMonHoc()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div><input type="submit" value="Tìm kiếm"><c:if test="${role.equals('admin')}"><button class="add" type="button" onclick="window.location.href = 'AddTeacher'">+ Thêm</button></c:if></div>
                    </form>
                </div>
                <div class="teacherData">
                    <table class="data">
                        <tr>
                            <th>Tên GV</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Môn giảng dạy</th>
                            <c:if test="${role.equals('admin')}">
                            <th>Sửa</th>
                            <th>Xoá</th>
                            </c:if>
                    </tr>
                    <c:forEach items="${dsGiaoVien}" var="i">
                        <tr>
                            <td>${i.getTenGV()}</td>
                            <td>${i.getNgaySinh()}</td>
                            <td>${i.isGioiTinh()?"Nam":"Nữ"}</td>
                            <td>${i.getMonHoc().getTenMonHoc()}</td>
                            <c:if test="${role.equals('admin')}">
                                <td><button class="updateButton" onclick="window.location.href = 'UpdateTeacher?MaGV=${i.getMaGV()}'"s>🖊</button></td>
                                <td><button class="deleteButton" onclick="doDelete('${i.getMaGV()}')">X</button></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
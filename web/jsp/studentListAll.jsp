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
        <title>Danh sách học sinh</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
        <script>
            function doDelete(id) {
                if (confirm("Are you sure?")) {
                    window.location = "DeleteStudent?MaHS=" + id; //URL cộng thêm
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="myContainer">
            <div>
                <h1>Danh sách học sinh</h1>
            </div>
            <div class="filter">
                <form action="ViewStudents" method="post">
                    <div>Theo tên: <input type="text" name="hocSinhCanTim" value="${hocSinhCanTim}" placeholder="Tìm kiếm..."></div>
                    <div>Theo lớp:   
                        <select name="lopCanTim">
                            <option value="all">-Tất cả-</option>
                            <c:forEach items="${dsLop}" var="i">
                                <option value="${i.getMaLop()}" <c:if test="${String.valueOf(i.getMaLop()).equals(lopCanTim)}">selected</c:if>>${i.getTenLop()}</option>
                            </c:forEach>
                        </select></div>
                    <div><label for="sapXep">Sắp xếp học sinh</label>
                        <input type="checkbox" id="sapXep" name="sapXep" value="sapXep" <c:if test="${sapXep!=null}">checked</c:if>>
                        </div>
                        <div><input type="submit" value="Tìm kiếm"><c:if test="${role.equals('admin')}"><button class="add" type="button" onclick="window.location.href = 'AddStudent'">+ Thêm</button></c:if></div>
                    </form>
                </div>
                <div class="studentData">
                    <table class="data">
                        <tr>
                            <th>MSHS</th>
                            <th>Họ</th>
                            <th>Tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Lớp</th>
                            <c:if test="${role.equals('admin')}">
                            <th>Sửa</th>
                            <th>Xoá</th>
                            </c:if>
                    </tr>
                    <c:forEach items="${dsHocSinh}" var="i">
                        <tr>
                            <td>${i.getMaHS()}</td>
                            <td>${i.getHoDem()}</td>
                            <td>${i.getTen()}</td>
                            <td>${i.getNgaySinh()}</td>
                            <td>${i.isGioiTinh()?"Nam":"Nữ"}</td>
                            <td>${i.getLop().getTenLop()}</td>
                            <c:if test="${role.equals('admin')}">
                                <td><button class="updateButton" onclick="window.location.href = 'UpdateStudent?MaHS=${i.getMaHS()}'">🖊</button></td>
                                <td><button class="deleteButton" onclick="doDelete('${i.getMaHS()}')">X</button></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : studentListOnly
    Created on : Mar 5, 2024, 12:41:07 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="myContainer">
            <h2>DS học sinh lớp ${lopCanTim}:</h2>
            <div class="studentData">
                <table class="data">
                    <tr>
                        <th>MSHS</th>
                        <th>Họ</th>
                        <th>Tên</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>
                        <th>Lớp</th>
                    </tr>
                    <c:forEach items="${dsHocSinh}" var="i">
                        <tr>
                            <td>${i.getMaHS()}</td>
                            <td>${i.getHoDem()}</td>
                            <td>${i.getTen()}</td>
                            <td>${i.getNgaySinh()}</td>
                            <td>${i.isGioiTinh()?"Nam":"Nữ"}</td>
                            <td>${i.getLop().getTenLop()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>

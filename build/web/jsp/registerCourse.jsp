<%-- 
    Document   : registerCourse
    Created on : Mar 7, 2024, 11:30:34 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.MonHoc,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký thi</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="myContainer">
            <div class="register-semester">
                Chọn học kỳ:
                <select id="MaHK" name="MaHK" onchange="window.location.href = 'RegisterCourse?MaHK=' + document.getElementById('MaHK').value">
                    <c:forEach items="${dsHocKi}" var="i">
                        <option value="${i.getMaHocKi()}" ${i.getMaHocKi()==MaHK?'selected':''}>${i.getTenHocKi()}</option>
                    </c:forEach>
                </select>
            </div>

            <table class="register-table">
                <tr>
                    <th>Môn học</th>
                    <th>Thêm</th>
                    <th>Xoá</th>
                </tr>
                <%
                    Vector<MonHoc> dsMonHoc = (Vector<MonHoc>) request.getAttribute("dsMonHoc");
                    if(dsMonHoc != null){
                    for(MonHoc mh : dsMonHoc){
                %>
                <tr>
                    <td><%=mh.getTenMonHoc()%></td>
                    <%
                        if(request.getAttribute("thongbao" + mh.getMaMonHoc()) == null){
                    %>
                    <td><button type="button" class="register-add" onclick="window.location.href = 'RegisterCourse?dichVu=them&MaMonHoc=<%=mh.getMaMonHoc()%>&MaHK=${MaHK}'">+</button></td>
                    <td><button type="button" class="register-remove" onclick="window.location.href = 'RegisterCourse?dichVu=xoa&MaMonHoc=<%=mh.getMaMonHoc()%>&MaHK=${MaHK}'">-</button></td>
                    <%
                        }else{
                    %>
                    <td colspan="2"><b>Đã đăng ký</b></td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                    }
                %>
            </table>
            <button type="button" class="register-viewcart" onclick="window.location.href = 'ViewCart'">☰ Xem danh sách</button>
            ${message}
        </div>
    </body>
</html>

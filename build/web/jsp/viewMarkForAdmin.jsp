<%-- 
    Document   : viewMark
    Created on : Feb 4, 2024, 11:24:03 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xem điểm</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="myContainer">
            <h1>Điểm học sinh</h1>
            <div class="viewMark">
                <form action="ViewMarkList">
                    Lớp:
                    <select name="lopCanTim">
                        <c:forEach items="${dsLop}" var="i">
                            <option value="${i.getMaLop()}" <c:if test="${i.getMaLop()==lopCanTim}">selected</c:if>>${i.getTenLop()}</option>
                        </c:forEach>
                    </select>
                    Môn:
                    <select name="monHocCanTim">
                        <c:forEach items="${dsMonHoc}" var="i">
                            <option value="${i.getMaMonHoc()}" <c:if test="${i.getMaMonHoc()==monHocCanTim}">selected</c:if>>${i.getTenMonHoc()}</option>
                        </c:forEach>
                    </select>
                    Học kì:
                    <select name="hocKiCanTim">
                        <c:forEach items="${dsHocKi}" var="i">
                            <option value="${i.getMaHocKi()}" <c:if test="${i.getMaHocKi()==hocKiCanTim}">selected</c:if>>${i.getTenHocKi()}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Tìm">
                </form>
                <form action="ViewMarkList" method="post">
                    <div class="studentData">
                        <table class="data">
                            <tr>
                                <th>Mã HS</th>
                                <th>Họ</th>
                                <th>Tên</th>
                                <th>Ngày sinh</th>
                                <th>Điểm</th>
                            </tr>
                            <%
                                ResultSet rs = (ResultSet) request.getAttribute("dsDiem");
                                if(rs != null){
                                    while(rs.next()){%>
                            <tr>
                                <td><%=rs.getInt(1)%></td>
                                <td><%=rs.getString(2)%></td>
                                <td><%=rs.getString(3)%></td>
                                <td><%=rs.getString(4)%></td>
                                <td class="inputArea"><input type="text" name="<%=rs.getInt(1)%>" value="<%=rs.getFloat(5)==-1?0.0:String.format("%.1f",rs.getFloat(5))%>"></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                        <input type="hidden" name="monHocCanTim" value="${monHocCanTim}">
                        <input type="hidden" name="lopCanTim" value="${lopCanTim}">
                        <input type="hidden" name="hocKiCanTim" value="${hocKiCanTim}">
                        <%if(rs != null && rs.isAfterLast()){%>
                        <button type="button" onclick="checkInputMark(this)">Lưu</button>
                        <%}%>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function checkInputMark(button) {
                var inputMark = document.getElementsByClassName("inputArea");
                var isAllFloats = true;
                for (var i = 0; i < inputMark.length; i++) {
                    var inputValue = Number(inputMark[i].querySelector('input').value);
                    if (isNaN(inputValue) || inputValue < 0 || inputValue > 10) {
                        isAllFloats = false;
                        break;
                    }
                }
                if (isAllFloats) {
                    button.type = 'submit';
                } else {
                    alert('Vui lòng nhập số hợp lệ từ 0 đến 10!');
                }
            }
        </script>
    </body>
</html>

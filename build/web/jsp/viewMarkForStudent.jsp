<%-- 
    Document   : viewMarkForStudent
    Created on : Feb 6, 2024, 11:37:43 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Điểm</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="myContainer">
            <h1>Báo cáo điểm</h1>
            <div class="viewMark">
                <form action="ViewMarkList">
                    Học kỳ: 
                    <select name="hocKiCanTim">
                        <c:forEach items="${dsHocKi}" var="i">
                            <option value="${i.getMaHocKi()}" <c:if test="${i.getMaHocKi()==hocKiCanTim}">selected</c:if>>${i.getTenHocKi()}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Tìm">
                </form>
                <c:if test="${hocKiCanTim != null}">
                    <table>
                        <tr>
                            <c:forEach items="${dsMonHoc}" var="i">
                                <th>${i.getTenMonHoc()}</th>
                                </c:forEach>
                        </tr>
                        <tr>
                            <%
                                int sum = 0;double avg = 0;
                                boolean isAllFilled = true;
                                ResultSet rs = (ResultSet) request.getAttribute("dsDiem");
                                if(rs != null){
                                while(rs.next()){
                                    if(rs.getFloat(3)==-1){
                                        isAllFilled = false;
                                    }
                                sum++; avg += rs.getFloat(3);
                            %>
                            <td><%=rs.getFloat(3)==-1?"N/A":rs.getFloat(3)%></td>
                            <%}
                                }
                            if(!isAllFilled){
                                avg = -1; sum = 0;
                            }
                            %>
                        </tr>
                    </table>
                    <br>
                    <table>
                        <tr>
                            <td>ĐIỂM T.KẾT</td>
                            <td><b>
                                    <%avg = avg/sum;%>
                                    <%=Double.isInfinite(avg)?"Chưa có điểm":String.format("%.2f",avg)%>
                                </b>
                            </td>
                        </tr>
                        <tr>
                            <td>TÌNH TRẠNG:</td>
                            <td class="markStatus"><%
                        if(avg >= 5){ %>
                                <b style="color:green">HOÀN THÀNH</b>
                                <%}else if(avg >=0 && avg < 5){%>
                                <b style="color:red">HỌC LẠI</b>
                                <%}else{%>
                                <b>CHƯA CÓ ĐIỂM</b>
                                <%}
                                %>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
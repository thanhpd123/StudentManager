<%-- 
    Document   : cart
    Created on : Mar 7, 2024, 11:37:31 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.MonHocDAO,dal.HocKiDAO,model.MonHoc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin đăng ký</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="myContainer">
            <form action="ViewCart" method="post">
                <table>
                    <tr>
                        <th>Môn</th>
                        <th>Học kỳ</th>
                        <th>Số lượng</th>
                        <th>Thêm</th>
                        <th>Xoá</th>
                    </tr>

                    <%
                        java.util.Enumeration em = session.getAttributeNames();
                        boolean hasData = false;
                        while (em.hasMoreElements()) {
                            String id = em.nextElement().toString();
                            if (!id.equals("studentID") && !id.equals("role") && !id.equals("account")) {
                            hasData = true;
                    %>
                    <tr>
                        <%
                            MonHocDAO mhdao = new MonHocDAO();
                            MonHoc mh = mhdao.getMonHocByMaMonHoc(id.substring(0,1));
                        %>
                        <td><%=mh.getTenMonHoc()%></td>
                        <td><%=id.substring(1)%></td>
                        <td><%=session.getAttribute(id).toString()%></td>
                        <td><button type="button" class="register-add" onclick="window.location.href='ViewCart?dichVu=them&MaMonHoc=<%=mh.getMaMonHoc()%>&MaHK=<%=id.substring(1)%>'">+</button></td>
                        <td><button type="button" class="register-remove" onclick="window.location.href='ViewCart?dichVu=xoa&MaMonHoc=<%=mh.getMaMonHoc()%>&MaHK=<%=id.substring(1)%>'">-</button></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
                <div class="cart-register">
                    <%if(hasData){%><input type="submit" value="✓ Đăng ký"><%}%>
                    <input type="button" value="⏎ Quay lại" onclick="window.location.href='RegisterCourse'">
                </div>
            </form>
            ${message}
        </div>
    </body>
</html>

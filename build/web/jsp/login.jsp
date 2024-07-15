<%-- 
    Document   : login
    Created on : Jan 25, 2024, 9:55:02 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <style>
            <%@ include file="../css/style.css"%>
        </style>
    </head>
    <body>
        <form action="login" method="post">
            <div class="login-form">
                <h1>Đăng nhập</h1>
                <div>Nhập ID: <input type="text" name="user"> </div>
                <br>
                <input type="submit" value="Đăng nhập" />
                <p style="color:red">${error}</p>
            </div>
        </form>
    </body>
</html>

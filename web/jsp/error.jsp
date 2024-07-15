<%-- 
    Document   : error
    Created on : Jan 25, 2024, 10:32:55 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lỗi</title>
        <style>
            body{
                font-family: 'Arial';
                background-image: url(../img/body.jpg);
                background-repeat: no-repeat;
                background-size: 100% 100%;
                background-attachment: fixed;
                background-position: center center;
            }
            img{
                width: 50%;
            }
            div{
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            button{
                border: none;
                border-radius: 5px;
                background-color: #cccccc;
                padding: 10px 5px;
            }
            button:hover{
                background-color: #999999;
            }
        </style>
    </head>
    <body>
        <div>
            <h1 style="color:red">Bạn không có quyền truy cập!</h1>
            <img src="../img/notaccess.png" alt="Not Access"/>
            <br>
            <button onclick="window.location.href = '../login'">Quay lại trang đăng nhập</button>
        </div>
    </body>
</html>
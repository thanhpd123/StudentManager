<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <img src="img/banner.jpg"/>
    <div class="header-content">
        <div class="header-content-user">
            <h4>Xin chào ${account}!</h4> &nbsp;&nbsp;
            <button onclick="window.location.href = 'login'">Đăng xuất</button>
        </div>
        <button onclick="window.location.href = 'ViewStudents'">Danh sách học sinh</button>
        <button onclick="window.location.href = 'ViewTeachers'">Danh sách giáo viên</button>
        <button onclick="window.location.href = 'ViewMarkList'">Xem điểm</button>
        <c:if test="${role.equals('student')}">
            <button onclick="window.location.href = 'RegisterCourse'">Đăng ký thi</button>
        </c:if>
    </div>
</header>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<!-- 关于Cookie的：将数据保存在浏览器中，以后每次请求都携带Cookie数据进行访问
<form action="Servlet01" method="post">
    用户名：<input type="text" name="uname"><br>
    密 码：<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
-->

<!-- 关于Session的：将数据保存在服务器中，来实现一次会话的多次请求间数据共享功能 -->
<form action="Servlet04" method="post">
    班级ID：<input type="text" name="uId"><br>
    用户ID：<input type="text" name="classId"><br>
    用户名：<input type="text" name="uName"><br>
    密  码：<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
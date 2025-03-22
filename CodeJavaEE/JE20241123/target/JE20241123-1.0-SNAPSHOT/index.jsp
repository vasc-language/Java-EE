<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello Servlet</title>
</head>
<body>
<h1>用户登录</h1>
<form action="ServletSelect" method="post">
    用户ID：<input type="text" name="uname"><br>
    学 号：<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
<%--<a href="ServletUpdate">ServletUpdate</a>--%>
<%--<a href="ServletSelect">ServletSelect</a>--%>
</body>
</html>
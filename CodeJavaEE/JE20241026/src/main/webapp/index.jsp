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
</body>
<form action="Servlet01" method="post">
    用户名：<input type="text" name="uname"><br>
    密  码：<input type="password" name="pwd"><br>
    <input type="submit" value="登录"><br>
</form>
</html>
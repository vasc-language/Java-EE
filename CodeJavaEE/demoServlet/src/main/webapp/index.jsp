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
<form method="post" action="myJSP02.jsp" >
    用户名：<input type="text" name="uname"><br>
    密 码：<input type="password" name="pwd"><br>
    爱 好：<input type="checkbox" name="checkbox" value="1">游泳
    <input type="checkbox" name="checkbox" value="2">钓鱼
    <input type="submit">
</form>
</html>



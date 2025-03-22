<%--
  Created by IntelliJ IDEA.
  User: 姚东名
  Date: 2024/11/2
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入JSTL标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudentsList</title>
</head>
<body>
<!-- Expression Language(EL表达式) 表达式语言，用于简化JSP页面的Java代码 -->
<table border="1" cellpadding="0" width="800">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr align="center">
            <td>${student.sId}</td>
            <td>${student.sName}</td>
            <td>${student.age}</td>
            <c:if test="${student.gender == 1}">
                <td>男</td>
            </c:if>
            <c:if test="${student.gender != 1}">
                <td>女</td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>

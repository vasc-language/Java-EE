<%--
  Created by IntelliJ IDEA.
  Description: 引用JSTL标签库写Java代码（更方便）
  User: 姚东名
  Date: 2024/10/25
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入JSTL标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<!--Expression Language（EL表达式） 表达式语言，用于简化 JSP页面内的Java代码: ${students}-->
<table border="1" cellpadding="0" width="800">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <!--开始引用JSTL标签库：forEach遍历、if判断语句-->
    <c:forEach items="${students}" var="student">
        <tr align="center">
            <td>${student.id}</td>
            <td>${student.name}</td>
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

<%--
  Created by IntelliJ IDEA.
  User: 姚东名
  Date: 2024/11/23
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>studentList</title>
</head>
<body>
${students};
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>学生ID</th>
        <th>学号</th>
        <th>姓名</th>
        <th>学生邮箱</th>
        <th>班级ID</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr align="center">
            <td>${student.id}</td>
            <td>${student.sn}</td>
            <td>${student.uname}</td>
            <td>${student.mail}</td>
            <td>${student.classId}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

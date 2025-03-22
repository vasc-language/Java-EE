
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demoservlet.Demo3.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: 
  Date: 2024/10/21
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>students</title>
</head>
<body>
<%
    List<Student> students = new ArrayList<>();
    students.add(new Student(23340103, "曹汝庆", 20, 1));
    students.add(new Student(2, "张三", 20, 0));
    students.add(new Student(3, "李四", 20, 1));
    students.add(new Student(4, "王五", 20, 0));

%>

<table border="1" cellspacing="0" width="800">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <%-- 在jsp文件写Java代码，遍历students --%>
    <% for (int i = 0; i < students.size(); i++) {
        Student student = students.get(i);
    %>
    <tr align="center">
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <%if (student.getGender() == 1) {%>
        <td>男</td>
        <% } %>
        <%if (student.getGender() == 0) {%>
        <td>女</td>
        <% } %>
    </tr>
    <% } %>
</table>
</body>
</html>

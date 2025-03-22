<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.je20241026.Mode.Student" %>
<%--
  Created by IntelliJ IDEA.
  Description: 在JSP文件中写Java代码
  User: 姚东名
  Date: 2024/10/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<!-- 在JSP文件写Java代码 -->
<%
    //创建存储学生对象的集合
    List<Student> students = new ArrayList<>();
    // 往集合中添加学生数据
    students.add(new Student(1, "张三", 11, 1));
    students.add(new Student(2, "李四", 11, 1));
    students.add(new Student(3, "王五", 11, 1));
    students.add(new Student(4, "赵六", 11, 1));
%>
<!--
    table标签：表
    tr标签：每一行
    th标签：行中的表头
    td标签：每一列
-->

<table border="1" cellpadding="0" width="800">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>

    <%
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
    %>
    <tr align="center">
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <%
            if (student.getGender() == 1) {
        %>
        <td>男</td>
        <%
            }
        %>
        <%
            if (student.getGender() != 1) {
        %>
        <td>女</td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

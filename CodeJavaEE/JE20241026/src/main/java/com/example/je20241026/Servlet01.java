package com.example.je20241026;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-25
 * Time: 19:26
 */

import com.example.je20241026.Mode.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet01", value = "/Servlet01")
public class Servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        //将学生对象放到集合中
        students.add(new Student(1, "张三", 20, 1));
        students.add(new Student(2, "李四", 21, 0));
        students.add(new Student(3, "王五", 22, 1));
        students.add(new Student(4, "赵六", 23, 0));
        //请求转发：
        //void setAttribute(String name, Object o)：存储数据到 request域中
        request.setAttribute("students", students);
        //开始请求转发
        request.getRequestDispatcher("myjsp02.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

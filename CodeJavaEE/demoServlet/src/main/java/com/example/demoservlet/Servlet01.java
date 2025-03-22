package com.example.demoservlet;
/**
 * Created with IntelliJ IDEA.
 * Description: 请求转发到 myjsp.jsp文件打印students
 * User: 姚东名
 * Date: 2024-10-21
 * Time: 21:53
 */

import com.example.demoservlet.Demo3.Student;

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
        students.add(new Student(1, "姚东铭", 20, 1));
        students.add(new Student(2, "张三", 20, 0));
        students.add(new Student(3, "李四", 20, 0));
        students.add(new Student(4, "王五", 20, 0));
        /**
         * void setAttribute(String name, Object o)：存储数据到 request域中
         * Object getAttribute(String name)：根据 key，获取值
         *  void removeAttribute(String name)：根据 key，删除该键值对
         */
        request.setAttribute("students", students);
        request.getRequestDispatcher("/myjsp01.jsp").forward(request, response);
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println("Student{" +
                    "id=" + student.getId() +
                    ", name='" + student.getName() + '\'' +
                    ", age=" + student.getAge() +
                    ", gender=" + student.getGender() +
                    '}');
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
package com.example.je20241031; /**
 * Created with IntelliJ IDEA.
 * Description: 根据用户的班级ID  获取不同班级学生的列表 在前台显示
 * User: 姚东名
 * Date: 2024-11-02
 * Time: 11:05
 */

import com.example.je20241031.mode.Student;
import com.example.je20241031.mode.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet05", value = "/Servlet05")
public class Servlet05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //根据Key，获取Value
        User user = (User) session.getAttribute("user");

        //创建班级1的学生列表
        List<Student> students01 = new ArrayList<>();
        students01.add(new Student("23340102", "张三", 21, 1));
        students01.add(new Student("23340103", "李四", 20, 1));
        students01.add(new Student("23340104", "小红", 18, 0));
        //创建班级2的学生列表
        List<Student> students02 = new ArrayList<>();
        students02.add(new Student("23340202", "王五", 21, 1));
        students02.add(new Student("23340203", "赵六", 20, 1));
        students02.add(new Student("23340204", "小莉", 18, 0));

        //登录用户（老师）查看自己的班级表
        if (user.getClassId().equals("01")) {
            request.setAttribute("students", students01);
        } else {
            request.setAttribute("students", students02);
        }

        //请求转发：
        request.getRequestDispatcher("jsp05.jsp").forward(request, response);
        //根据用户ID 获取不同班级学生的列表 在前台显示
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

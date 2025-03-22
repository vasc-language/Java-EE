package com.example.je20241222.mode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-23
 * Time: 12:36
 */
public class Student {
    private String id; // ID
    private String uname; // 姓名
    private String pwd; // 密码
    private int age; // 年龄
    private int xb; // 性别

    public Student(String id, String uname, String pwd, int age, int xb) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.age = age;
        this.xb = xb;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getXb() {
        return xb;
    }

    public void setXb(int xb) {
        this.xb = xb;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", xb=" + xb +
                '}';
    }
}

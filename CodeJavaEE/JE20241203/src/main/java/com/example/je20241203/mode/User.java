package com.example.je20241203.mode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 10:28
 */
public class User {
    private  String id;
    private  String uname;
    private  String pwd;
    private  int age;
    private  int xb;

    public User() {
    }

    public User(String id, String uname, String pwd, int age, int xb) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.age = age;
        this.xb = xb;
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
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", xb=" + xb +
                '}';
    }
}

package com.example.je20241222.mode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-22
 * Time: 22:36
 */
public class User {
    private String uname; // 姓名
    private String password; // 密码

    public User(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public User() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

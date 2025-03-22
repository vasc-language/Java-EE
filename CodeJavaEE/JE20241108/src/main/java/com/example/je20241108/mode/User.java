package com.example.je20241108.mode;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户对象的内容
 * User: 姚东名
 * Date: 2024-11-08
 * Time: 20:08
 */
public class User {
    //用户名、密码
    private String uname;
    private String password;

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

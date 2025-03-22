package com.example.je20241123;
/**
 * Created with IntelliJ IDEA.
 * Description: user类 对象
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 15:40
 */
public class User {
    private String id; // id号
    private String uname; // 姓名
    private String password; // 密码
    private int age; // 年龄
    private int gender; //性别

    public User(String id, String uname, String password, int age, int gender) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

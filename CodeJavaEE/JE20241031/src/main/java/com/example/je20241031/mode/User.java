package com.example.je20241031.mode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-02
 * Time: 10:25
 */
public class User {
    public String uName;
    public String uId;
    public String password;
    public String classId;

    public User(String uName, String uId, String password, String classId) {
        this.uName = uName;
        this.uId = uId;
        this.password = password;
        this.classId = classId;
    }

    public User() {
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uName='" + uName + '\'' +
                ", uId='" + uId + '\'' +
                ", password='" + password + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}

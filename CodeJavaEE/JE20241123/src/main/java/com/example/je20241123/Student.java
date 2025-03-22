package com.example.je20241123;

/**
 * Created with IntelliJ IDEA.
 * Description: Student对象
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 16:37
 */
public class Student {
    private String id; // 学生id
    private String sn; // 学号
    private String uname; // 姓名
    private String mail; // 邮件号
    private int classId; // 学生id

    public Student() {
    }

    public Student(String id, String sn, String uname, String mail, int classId) {
        this.id = id;
        this.sn = sn;
        this.uname = uname;
        this.mail = mail;
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", uname='" + uname + '\'' +
                ", mail='" + mail + '\'' +
                ", classId=" + classId +
                '}';
    }
}

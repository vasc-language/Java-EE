package com.example.je20241031.mode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-02
 * Time: 10:28
 */
public class Student {
    public String sId;
    public String sName;
    public int age;
    public int gender;

    public Student(String sId, String sName, int age, int gender) {
        this.sId = sId;
        this.sName = sName;
        this.age = age;
        this.gender = gender;
    }

    public Student() {
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
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
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

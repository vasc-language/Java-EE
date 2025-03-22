package com.example.je20250130;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-01
 * Time: 10:58
 */
public class UserInfo {
    private String name;
    private int gender;
    private int age;

    public UserInfo(String name, int gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public UserInfo() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}

package com.example.demoservlet.Demo;

public class Student {
    public int id;
    public String name;
    public int age;
    public int gender;

    public Student(int id, String name, int age, int gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    //无参的构造方法
    public Student(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
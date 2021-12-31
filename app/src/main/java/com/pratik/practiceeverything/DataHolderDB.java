package com.pratik.practiceeverything;

public class DataHolderDB {
    String name, age, number, img;

    public DataHolderDB(String name, String age, String number, String img) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

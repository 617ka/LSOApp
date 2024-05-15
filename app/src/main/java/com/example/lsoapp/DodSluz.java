package com.example.lsoapp;

public class DodSluz {
    private String id;
    private String name;
    private String day;
    private int hour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "DodSluz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}

package com.example.lsoapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dodajS")
public class DodanieSluzby {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Sluzb")
    int id;
    @ColumnInfo(name = "Imie")
    private String name;
    @ColumnInfo(name = "Dzien")
    private String day;
    @ColumnInfo(name = "Godzina")
    private int hour;

    public DodanieSluzby(String name, String day, int hour) {
        id = 0;
        this.name = name;
        this.day = day;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return  "Imię: "+ name +"| Dzień: "+ day +" | Godzina: "+ hour;
    }

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
}

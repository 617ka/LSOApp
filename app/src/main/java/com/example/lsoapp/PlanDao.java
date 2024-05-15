package com.example.lsoapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDao {
    @Insert
    public void dodajOsobe(DodanieSluzby dodanieSluzby);
    @Delete
    public void usunOsobe(DodanieSluzby dodanieSluzby);
    @Query("SELECT * FROM dodajS")
    public List<DodanieSluzby> wypiszPlan();
    @Query("Select * from dodajS where dodajS.Imie =:imie")
    public List<DodanieSluzby> wybierzPoImieniu(String imie);
    @Query("SELECT * FROM dodajS WHERE dodajS.Dzien=:dzien")
    public List<DodanieSluzby> wybierzDzien(String dzien);
    @Query("select * from dodajS where dodajS.Godzina =:godzina")
    public List<DodanieSluzby> wybierzGodzine(int godzina);
}

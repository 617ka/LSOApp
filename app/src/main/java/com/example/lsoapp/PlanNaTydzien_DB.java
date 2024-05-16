package com.example.lsoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//klasa abstrakcyjna zwracająca instancję bazy danych
@Database(entities = {DodanieSluzby.class}, version = 1)
public abstract class PlanNaTydzien_DB extends RoomDatabase {

    private static PlanNaTydzien_DB instancja;
    public abstract PlanDao getPlanDao();

    public static PlanNaTydzien_DB zwrocInstancjeBazy(Context context){
        if (instancja == null){
            instancja = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PlanNaTydzien_DB.class,
                            "Plan Służby"
                    ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instancja;
    }
}

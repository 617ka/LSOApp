package com.example.lsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    PlanNaTydzien_DB planNaTydzien_db;
    public EditText imie;
    public EditText dzien;
    public EditText godzina;

    List<DodanieSluzby> list = new ArrayList<>();
    Button button;
    Button button2;

    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        planNaTydzien_db = PlanNaTydzien_DB.zwrocInstancjeBazy(getApplicationContext());

        imie = findViewById(R.id.imie);
        dzien = findViewById(R.id.dzien);
        godzina = findViewById(R.id.godzina);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(godzina.getText().toString()) == 7 || Integer.parseInt(godzina.getText().toString()) == 18) {
                            DodanieSluzby dodanieSluzby = new DodanieSluzby(imie.getText().toString(), dzien.getText().toString(), Integer.parseInt(godzina.getText().toString()));
                            planNaTydzien_db.getPlanDao().dodajOsobe(dodanieSluzby);
                            imie.setText("");
                            dzien.setText("");
                            godzina.setText("");
                        }
                        else{
                            godzina.setText("");
                        }
                    }
                }
        );

        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PlanTydzienList.class);
                        startActivity(intent);
                    }
                }
        );

        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pobierzBazeDanych();
                    }
                }
        );


    }

    public void pobierzBazeDanych() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/617ka/LSOApp/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call<List<DodanieSluzby>> call = jsonPlaceholderApi.getCzlonkow();
        call.enqueue(
                new Callback<List<DodanieSluzby>>() {
                    @Override
                    public void onResponse(Call<List<DodanieSluzby>> call, Response<List<DodanieSluzby>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                        } else {
                            for (DodanieSluzby item : response.body()) {
                                list.add(item);
                            }
                            for (DodanieSluzby item1 : list) {
                                planNaTydzien_db.getPlanDao().dodajOsobe(item1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DodanieSluzby>> call, Throwable t) {

                    }
                }
        );
    }
}
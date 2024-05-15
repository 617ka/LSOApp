package com.example.lsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    PlanNaTydzien_DB planNaTydzien_db;
    public EditText imie;
    public EditText dzien;
    public EditText godzina;

    Button button;
    Button button2;

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

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DodanieSluzby dodanieSluzby = new DodanieSluzby(imie.getText().toString(),dzien.getText().toString(),Integer.parseInt(godzina.getText().toString()));
                        planNaTydzien_db.getPlanDao().dodajOsobe(dodanieSluzby);
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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
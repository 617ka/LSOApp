package com.example.lsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanTydzienList extends AppCompatActivity {

    PlanNaTydzien_DB planNaTydzien_db;
    ListView listView;
    ArrayAdapter<DodanieSluzby> arrayAdapter;
    List<DodanieSluzby> sluzbyList = new ArrayList<>();
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plan_tydzien_list);

        planNaTydzien_db = PlanNaTydzien_DB.zwrocInstancjeBazy(getApplicationContext());

        listView = findViewById(R.id.listaSluzb);
        sluzbyList = planNaTydzien_db.getPlanDao().wypiszPlan();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sluzbyList);
        listView.setAdapter(arrayAdapter);


        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlanTydzienList.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );









    }
}
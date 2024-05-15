package com.example.lsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class PlanTydzienList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    PlanNaTydzien_DB planNaTydzien_db;
    ListView listView;
    ArrayAdapter<DodanieSluzby> arrayAdapter;
    List<DodanieSluzby> sluzbyList = new ArrayList<>();
    Button button3;
    Button button4;

    String[] wybory = {"Imię", "Dzień", "Godzina"};
    String wybrany;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plan_tydzien_list);


        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                wybory);
        adapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        planNaTydzien_db = PlanNaTydzien_DB.zwrocInstancjeBazy(getApplicationContext());

        listView = findViewById(R.id.listaSluzb);





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

        editText = findViewById(R.id.editTextWpisane);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(wybrany == "Imię"){
                            sluzbyList = planNaTydzien_db.getPlanDao().wybierzPoImieniu(editText.getText().toString());
                        }
                        else if(wybrany == "Dzień"){
                            sluzbyList = planNaTydzien_db.getPlanDao().wybierzDzien(editText.getText().toString());
                        }else{
                            sluzbyList = planNaTydzien_db.getPlanDao().wybierzGodzine(Integer.parseInt(editText.getText().toString()));
                        }
                        arrayAdapter = new ArrayAdapter<>(PlanTydzienList.this, android.R.layout.simple_list_item_1, sluzbyList);
                        listView.setAdapter(arrayAdapter);
                    }
                }
        );

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sluzbyList = planNaTydzien_db.getPlanDao().wypiszPlan();
                        arrayAdapter = new ArrayAdapter<>(PlanTydzienList.this, android.R.layout.simple_list_item_1, sluzbyList);
                        listView.setAdapter(arrayAdapter);
                    }
                }
        );

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        wybrany = wybory[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
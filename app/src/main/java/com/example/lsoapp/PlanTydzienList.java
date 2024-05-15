package com.example.lsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlanTydzienList extends AppCompatActivity {

    PlanNaTydzien_DB planNaTydzien_db;
    ListView listView;
    ArrayAdapter<DodanieSluzby> arrayAdapter;
    List<DodanieSluzby> sluzbyList = new ArrayList<DodanieSluzby>();
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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
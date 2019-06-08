package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.closereveryday.adapter.SomeDataRecyclerAdapter;
import com.example.closereveryday.db.DatabaseHelper;
import com.example.closereveryday.db.model.DataModel;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Base extends AppCompatActivity implements SomeDataRecyclerAdapter.OnDeleteListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public DatabaseHelper databaseHelper;

    public static EditText nameid, describe, password, opi, kolvo;
    public static String names, describes, passwords, np, dp, pp,tms;
    final String SAVED_TEXT = "saved_text";
    public int salam, money;
    public Boolean all;


    private LinearLayout mBackgroundLinearLayout;
    SharedPreferences sPref;

    public static App instance;
    public SomeDataRecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osnova);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(getBottomNavigationListener());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        names = sharedPreferences.getString("name","");
        nameid = (EditText) findViewById(R.id.nameid4);
        nameid.setText(names);

        passwords = sharedPreferences.getString("password", "");
        password = (EditText) findViewById(R.id.khg6);
        password.setText(passwords);

        describes = sharedPreferences.getString("describe", "");
        describe = (EditText) findViewById(R.id.khg7);
        describe.setText(describes);

        bnv.setSelectedItemId(R.id.action_money);

        ButterKnife.bind(this);

        opi = (EditText) findViewById(R.id.whyid);
        kolvo = (EditText) findViewById(R.id.kolvoid);
        kolvo.setText("0");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Base.this);
        recyclerView.setLayoutManager(layoutManager);
        databaseHelper = App.getInstance().getDatabaseInstance();

        recyclerAdapter = new SomeDataRecyclerAdapter(this, databaseHelper.getDataDao().getAllData());
    }


    @Override
    protected void onResume() {
        super.onResume();
        recyclerAdapter.setOnDeleteListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onDelete(DataModel dataModel) {
        databaseHelper.getDataDao().delete(dataModel);
        recyclerAdapter.addItem(dataModel);
        recyclerAdapter.notifyDataSetChanged();
    }


    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        final ScrollView sett = findViewById(R.id.lat);
        final ScrollView money = findViewById(R.id.money);
        final LinearLayout two = findViewById(R.id.two);
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_help:
                        sett.setVisibility(View.VISIBLE);
                        money.setVisibility(View.GONE);
                        two.setVisibility(View.GONE);
                        break;

                    case R.id.action_aim:
                        recyclerAdapter.notifyDataSetChanged();
                        sett.setVisibility(View.GONE);
                        money.setVisibility(View.GONE);
                        two.setVisibility(View.VISIBLE);
                        break;

                    case R.id.action_money:
                        money.setVisibility(View.VISIBLE);
                        sett.setVisibility(View.GONE);
                        two.setVisibility(View.GONE);
                        break;

                }
                return true;
            }
        };
    }


    public void onCliclMoney(View v) {
        money = Integer.parseInt(kolvo.getText().toString());
        switch(v.getId()) {
            case R.id.plus_ten:
                money += 10;
                break;
            case  R.id.plus_hundred:
                money += 100;
                break;
            case  R.id.plus_thousand:
                money += 1000;
                break;
        }
        kolvo.setText(money + "");
    }


    public void onMyButtonClick(View v) {
        all = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        names = sharedPreferences.getString("name","");
        passwords = sharedPreferences.getString("password", "");
        names = sharedPreferences.getString("name","");
        np = nameid.getText().toString();
        dp = password.getText().toString();
        pp = describe.getText().toString();
        if(!names.equals(np) && !describes.equals(pp) && !passwords.equals(dp)) {
            all = false;
        }

        if(all) {
            Toast.makeText(this, "Вы ничего не изменили.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Base.this, TestPassword.class);
            startActivity(intent);
        }
    }

    public void VizOsn(View v) {
        Intent intent = new Intent(Base.this, Card.class);
        startActivity(intent);

    }
    public void Help(View v){
       Intent intent = new Intent(Base.this, Reset.class);
        startActivity(intent);
    }

    // 3 page
        public void rashodClick(View v){

            if(opi.getText().toString().equals("") || kolvo.getText().toString().equals("")|| kolvo.getText().toString().equals("0"))
                Toast.makeText(this, "Пожалуйста введите данные.", Toast.LENGTH_SHORT).show();
            else{
        salam = Integer.parseInt(kolvo.getText().toString());
        salam = salam * (-1);
        textnull();
            }
    }

    public void prihodClick(View v){
        if(opi.getText().toString().equals("") || kolvo.getText().toString().equals("") || kolvo.getText().toString().equals("0")) {
            Toast.makeText(this, "Пожалуйста введите данные.", Toast.LENGTH_SHORT).show();
        } else {
            salam = Integer.parseInt(kolvo.getText().toString());
            textnull();
        }
    }

    public void textnull(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        getDates();
        DataModel model = new DataModel();
        model.setTitle(salam);
        model.setDescription(opi.getText().toString());
        model.setTim(tms);
        databaseHelper.getDataDao().insert(model);
        recyclerAdapter.addItem(model);
        recyclerAdapter.notifyDataSetChanged();
        opi.setText("");
        kolvo.setText("0");
        Toast.makeText(this, "Successful.", Toast.LENGTH_SHORT).show();
    }

    public void getDates(){
        Date date = new Date();
        tms = date.toString();
    }

}
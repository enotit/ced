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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Osnova extends AppCompatActivity implements SomeDataRecyclerAdapter.OnDeleteListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public DatabaseHelper databaseHelper;

    public static EditText nameid, describe, password, opi, kolvo;
    public static String names, describes, passwords, np, dp, pp;
    final String SAVED_TEXT = "saved_text";
    public int salam, lastidd;
    public Boolean all;

    private LinearLayout mBackgroundLinearLayout;
    SharedPreferences sPref;

    public static App instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osnova);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(getBottomNavigationListener());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Osnova.this);
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

        opi = (EditText) findViewById(R.id.whyid);
        kolvo = (EditText) findViewById(R.id.kolvoid);
        kolvo.setText("0");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Osnova.this);
        recyclerView.setLayoutManager(layoutManager);
        databaseHelper = App.getInstance().getDatabaseInstance();
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SomeDataRecyclerAdapter recyclerAdapter = new SomeDataRecyclerAdapter(this, databaseHelper.getDataDao().getAllData());
        recyclerAdapter.setOnDeleteListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onDelete(DataModel dataModel) {
        databaseHelper.getDataDao().delete(dataModel);
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
                    case R.id.action_setting:
                        sett.setVisibility(View.VISIBLE);
                        money.setVisibility(View.GONE);
                        two.setVisibility(View.GONE);
                        break;

                    case R.id.action_aim:
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


    // 1 page

    public void onMyButtonClick(View v) {
        all = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Osnova.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        names = sharedPreferences.getString("name","");
        passwords = sharedPreferences.getString("password", "");
        names = sharedPreferences.getString("name","");
        np = nameid.getText().toString();
        dp = password.getText().toString();
        pp = describe.getText().toString();

            if(names.equals(np) & describes.equals(pp) & passwords.equals(dp)){
                }
            else all = false;

        if(all){
            Toast.makeText(this, "Вы ничего не изменили.", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(Osnova.this, test_passsword.class);
            startActivity(intent);
        }}
    public void VizOsn(View v) {
        Intent intent = new Intent(Osnova.this, vizitka.class);
        startActivity(intent);

    }
    public void Help(View v){
       Intent intent = new Intent(Osnova.this, sbros.class);
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
        if(opi.getText().toString().equals("") || kolvo.getText().toString().equals("") || kolvo.getText().toString().equals("0"))
            Toast.makeText(this, "Пожалуйста введите данные.", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(this, "dds", Toast.LENGTH_SHORT).show();
     salam = Integer.parseInt(kolvo.getText().toString());
       textnull();  }
    }

    public void textnull(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Osnova.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        DataModel model = new DataModel();
        model.setTitle(salam);
        model.setDescription(opi.getText().toString());
        databaseHelper.getDataDao().insert(model);

        opi.setText("");
        kolvo.setText("0");
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

    }




}
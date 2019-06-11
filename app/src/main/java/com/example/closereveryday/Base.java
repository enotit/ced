package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
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
    MediaPlayer minusmoney;
    public static EditText nameid, describe, password, opi, kolvo;
    public static String names, describes, passwords, np, dp, pp,tms;
    public static TextView balance;
    final String SAVED_TEXT = "saved_text";
    public int salam, money;
    private long millis;
    public Boolean all, mus;
    public Button bt;

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

        bt = (Button) findViewById(R.id.cancel_music);



        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        getDates();
        editor.putLong("enter_time",millis + 120000);
        editor.apply();

        bnv.setSelectedItemId(R.id.action_money);

        ButterKnife.bind(this);

        opi = (EditText) findViewById(R.id.whyid);
        kolvo = (EditText) findViewById(R.id.kolvoid);
        kolvo.setText("0");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Base.this);
        recyclerView.setLayoutManager(layoutManager);
        databaseHelper = App.getInstance().getDatabaseInstance();

        checkMus();
        buttonCancelMusic();
        recyclerAdapter = new SomeDataRecyclerAdapter(this, databaseHelper.getDataDao().getAllData());
        startMusichi();

        balance = (TextView) findViewById(R.id.Balance);
    }

    public void buttonCancelMusic(){
        checkMus();
        if(mus){
            bt.setVisibility(View.VISIBLE);
        }else{
            bt.setVisibility(View.INVISIBLE);
        }
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
        final LinearLayout two = findViewById(R.id.two);
        final ScrollView money = findViewById(R.id.money);


        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_help:
                        sett.setVisibility(View.VISIBLE);
                        money.setVisibility(View.GONE);
                        two.setVisibility(View.GONE);
                        mus_click();
                        break;

                    case R.id.action_aim:
                        recyclerAdapter.notifyDataSetChanged();
                        sett.setVisibility(View.GONE);
                        money.setVisibility(View.GONE);
                        two.setVisibility(View.VISIBLE);
                        set_Text_Sum();
                        mus_click();
                        break;

                    case R.id.action_money:
                        money.setVisibility(View.VISIBLE);
                        sett.setVisibility(View.GONE);
                        two.setVisibility(View.GONE);
                        buttonCancelMusic();
                        mus_click();
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

    public void mus_click(){
        checkMus();
        if(mus){
            int random_number1 = 1 + (int) (Math.random() * 3);
            switch (random_number1){
            case 1:
            minusmoney = MediaPlayer.create(this, R.raw.click_1);
            break;
            case 2:
             minusmoney = MediaPlayer.create(this, R.raw.click_2);
             break;
             case 3:
                 minusmoney = MediaPlayer.create(this, R.raw.click_3);
                 break;
            }

            minusmoney.start();
        }
    }


    public void set_Text_Sum(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int her = sharedPreferences.getInt("sum", 0);
        if(her < 0){
        balance.setTextColor(Color.rgb(200,0,0));
        balance.setText("Баланс: " + her);}
        else{
            balance.setTextColor(Color.rgb(0,150,60));
            balance.setText("Баланс: " + her);

        }
    }
    public void changeSetting(View v) {
        Intent intent = new Intent(Base.this, settings.class);
        startActivity(intent);
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

            if(opi.getText().toString().equals("") || kolvo.getText().toString().equals("")|| kolvo.getText().toString().equals("0")){
                Toast.makeText(this, "Пожалуйста введите данные.", Toast.LENGTH_SHORT).show();
            startMusicCancel();}
            else{
                startMusicmi();
        salam = Integer.parseInt(kolvo.getText().toString());
        salam = salam * (-1);
        textnull();
            }    }
    public void startMusicmi() {
        checkMus();
        if(mus){
        minusmoney = MediaPlayer.create(this, R.raw.muza);
        minusmoney.start();
        }
    }
    public void startMusicpri() {
        checkMus();
        if(mus){minusmoney = MediaPlayer.create(this, R.raw.prihadi);
        minusmoney.start();}
    }
    public void startMusichi(){
        checkMus();
        if(mus){minusmoney = MediaPlayer.create(this, R.raw.privet);
        minusmoney.start(); }
    }
    public  void startMusicCancel(){
        checkMus();
        if(mus){minusmoney = MediaPlayer.create(this, R.raw.nodata);
        minusmoney.start();}
    }
    public void stopMusic(View v){
        minusmoney.stop();
    }
    public void prihodClick(View v){
        if(opi.getText().toString().equals("") || kolvo.getText().toString().equals("") || kolvo.getText().toString().equals("0")) {
            Toast.makeText(this, "Пожалуйста введите данные.", Toast.LENGTH_SHORT).show();
            startMusicCancel();
        } else {
            startMusicpri();
            salam = Integer.parseInt(kolvo.getText().toString());
            textnull();
        }
    }

    public void textnull(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        getDates();
        int her = sharedPreferences.getInt("sum", 0);
        her = salam + her;
        editor.putInt("sum", her);

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
        editor.apply();
    }

    public void checkMus() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        mus = sharedPreferences.getBoolean("musBo",true);
    }

    public void getDates(){
        Date date = new Date();
        tms = date.toString();
        millis = date.getTime();
    }

}
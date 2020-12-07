package com.example.umorili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.umorili.R;

public class MainActivity extends AppCompatActivity {
    // пример взят с адреса:
    //http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_batton);
    }

    public void buttonClass(View view) {
        Intent intent = new Intent(this, OnRetrovitActivity.class);
        startActivity(intent);

    }

    public void buttonActivity(View view) {
        Intent intent = new Intent(this, RetrofitClassActivity.class);
        startActivity(intent);
    }

    public void buttonObservebler(View view) {
        Intent intent = new Intent(this,ObserverableActivity.class);
        startActivity(intent);
    }
}

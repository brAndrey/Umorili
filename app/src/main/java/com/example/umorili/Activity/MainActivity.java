package com.example.umorili.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili.PostModel;
import com.example.umorili.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





    }

    public void buttonClass(View view) {
        Intent intent = new Intent(this, OnRetrovitActivity.class);
        startActivity(intent);

    }

    public void buttonActivity(View view) {
        Intent intent = new Intent(this, RetrofitClassActivity.class);
        startActivity(intent);
    }
}

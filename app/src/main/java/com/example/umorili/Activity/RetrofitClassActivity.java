package com.example.umorili.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili.App;
import com.example.umorili.Constants;
import com.example.umorili.PostAdapter;
import com.example.umorili.PostModel;
import com.example.umorili.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClassActivity extends AppCompatActivity {

    private static final String LOG=RetrofitClassActivity.class.getName();

    RecyclerView recyclerView;
    List<PostModel> posts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        posts= new ArrayList<>();

        recyclerView = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostAdapter adapter = new PostAdapter( posts );
        recyclerView.setAdapter(adapter);

        if ( App.getApi() == null) {Log.i(LOG," Облом ");

        } else{ Log.i(LOG," not null ");}

//        try {
//            //Response response = App.getApi().getData("bash", 50).execute();
//
//            Response response = App.getApi().getData(Constants.RESOURSENAME,Constants.COINT).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        App.getApi().getData(Constants.RESOURSENAME,Constants.COINT).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                posts.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
                Toast.makeText(RetrofitClassActivity.this, "No error "+posts.size(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(RetrofitClassActivity.this, "An error occurred during networking",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

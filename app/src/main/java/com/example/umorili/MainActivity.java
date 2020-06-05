package com.example.umorili;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<PostModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts= new ArrayList<>();

        recyclerView = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostAdapter adapter = new PostAdapter( posts );
        recyclerView.setAdapter(adapter);

        try {
            Response response = App.getApi().getData("base", 50).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        App.getApi().getData("base", 50).enqueue(new Callback<List<PostModel>>() {
//            @Override
//            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                posts.addAll(response.body());
//                recyclerView.getAdapter().notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "No error "+posts.size(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "An error occurred during networking",Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}

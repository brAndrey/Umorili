package com.example.umorili.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili.api.App;
import com.example.umorili.Constants;
import com.example.umorili.adapter.PostAdapter;
import com.example.umorili.model.PostModel;
import com.example.umorili.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClassActivity extends AppCompatActivity {

    private static final String LOG=RetrofitClassActivity.class.getName();

    RecyclerView recyclerView;
    //List<PostModel> posts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_list);

       // posts= new ArrayList<>();

        recyclerView = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostAdapter adapter = new PostAdapter(  );
        recyclerView.setAdapter(adapter);

        if ( App.getRequestApi() == null) {Log.i(LOG," Облом ");

        } else{ Log.i(LOG," not null ");}

//        try {
//            //Response response = App.getApi().getData("bash", 50).execute();
//
//            Response response = App.getApi().getData(Constants.RESOURSENAME,Constants.COINT).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        App.getRequestApi().getData(Constants.RESOURSENAME,Constants.COINT).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                adapter.setPosts(response.body());

//                posts.addAll(response.body());
//                recyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(RetrofitClassActivity.this, "An error occurred during networking",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

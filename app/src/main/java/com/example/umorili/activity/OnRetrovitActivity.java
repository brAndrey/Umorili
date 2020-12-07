package com.example.umorili.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili.api.UmoriliApi;
import com.example.umorili.BuildConfig;
import com.example.umorili.Constants;
import com.example.umorili.adapter.PostAdapter;
import com.example.umorili.model.PostModel;
import com.example.umorili.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnRetrovitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //List<PostModel> posts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //posts= new ArrayList<>();

        recyclerView = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostAdapter adapter = new PostAdapter( );
        recyclerView.setAdapter(adapter);

        HttpLoggingInterceptor interceptor  = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);



        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        //подключаем интерфейс
        UmoriliApi umoriliApi  = retrofit.create(UmoriliApi.class);

        Call<List<PostModel>> messeges = umoriliApi.getData(Constants.RESOURSENAME,Constants.COINT);

        messeges.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    Log.i("onResponse", "  " + response.body().size());
                } else {
                    Log.i("onResponse code", "  " + response.code());
                }
                adapter.setPosts(response.body());
                //posts.addAll(response.body());
                //recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.i("onFailure", "  " );
            }
        });

    }

//        try {
//            Response response = App.getApi().getData("base", 50).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

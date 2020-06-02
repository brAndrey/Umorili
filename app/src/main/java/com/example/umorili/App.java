package com.example.umorili;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static UmoriliApi umoriliApi;

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
// логизация
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        // log of

        retrofit = new Retrofit.Builder()
                //Базовая часть адреса
                .baseUrl("https://umorili.herokuapp.com")
                //Конвертер, необходимый для преобразования JSON'а в объекты
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы

    }

    public static UmoriliApi getApi(){ return umoriliApi;}
}

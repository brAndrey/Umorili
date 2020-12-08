package com.example.umorili.api;

import android.app.Application;

import com.example.umorili.api.UmoriliApi;
import com.example.umorili.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static UmoriliApi umoriliApi;

    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // логизация
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        // log of

        retrofit = new Retrofit.Builder()
                //Базовая часть адреса
                .baseUrl(Constants.API_BASE_URI)
                //Конвертер, необходимый для преобразования JSON'а в объекты
                .addConverterFactory(GsonConverterFactory.create())
                // для Observerable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // логизация запроса
                .client(client)
                .build();

        umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы

    }

    public static UmoriliApi getRequestApi(){

        // без занесения строки
        //android:name=".api.App"
        // в манифест не работает
        return umoriliApi;}

}
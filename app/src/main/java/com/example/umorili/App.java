package com.example.umorili;

import android.app.Application;

import com.example.umorili.Api.UmoriliApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
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
                .client(client)
                .build();

        umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы

    }

    public static UmoriliApi getApi(){
//        if (umoriliApi == null){
//            // логизация
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            OkHttpClient.Builder client = new OkHttpClient.Builder()
//                    .addInterceptor(interceptor);
//            // log of
//
//            retrofit = new Retrofit.Builder()
//                    //Базовая часть адреса
//                    .baseUrl(Constants.API_BASE_URI)
//                    //Конвертер, необходимый для преобразования JSON'а в объекты
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы
//
//        }
        return umoriliApi;}

}
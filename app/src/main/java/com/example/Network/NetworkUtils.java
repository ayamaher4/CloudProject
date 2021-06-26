package com.example.Network;
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkUtils {

    String BASE_URL="https://newsapi.org/v2/";
    private  com.example.Network.ApiInterface apiInterface;
    public NetworkUtils(Context context){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create() ).build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface(){
        return apiInterface;
    }
    }

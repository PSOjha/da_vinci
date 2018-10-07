package com.example.theapache64.da_vinci.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "http://theapache64.com/mock_api/get_json/da_vinci/";

    private static RetrofitManager instance;
    private final APIInterface apiInterface;

    private RetrofitManager(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {

            final HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final APIInterface apiInterface = retrofit.create(APIInterface.class);

            instance = new RetrofitManager(apiInterface);
        }
        return instance;
    }

    public APIInterface getApiInterface() {
        return apiInterface;
    }
}

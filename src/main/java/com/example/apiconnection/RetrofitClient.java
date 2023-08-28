package com.example.apiconnection;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://imdb8.p.rapidapi.com";
    private static final String RAPID_API_KEY = "fc26e5bceemsh8238912a4b406dbp1e6e31jsn0776a216a692";
    private static final String RAPID_API_HOST = "imdb8.p.rapidapi.com";

    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()

                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                            .header("X-RapidAPI-Key", RAPID_API_KEY)
                            .header("X-RapidAPI-Host", RAPID_API_HOST)
                            .build()))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}

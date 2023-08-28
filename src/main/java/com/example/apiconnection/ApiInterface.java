package com.example.apiconnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/title/get-top-rated-movies")
    Call<List<id>> getMostPopularMovies();

    @GET("/title/get-details")
    Call<MovieResponse> getDetails(@Query("tconst") String titleId);

}

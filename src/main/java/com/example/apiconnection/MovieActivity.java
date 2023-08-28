//package com.example.apiconnection;
//
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import java.util.ArrayList;
//import java.util.List;
///
//import retrofit2.Callback;
//
//import android.view.View;
//import android.widget.ProgressBar;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import retrofit2.Call;
//import retrofit2.Response;
//
//public class MovieActivity extends AppCompatActivity {
//    RecyclerView recyclerView;
//    ProgressBar progressBar;
//    LinearLayoutManager layoutManager;
//    MovieAdapter adapter;
//    List<MovieItem> movieItems = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        progressBar = findViewById(R.id.progressBar);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new MovieAdapter(movieItems);
//        recyclerView.setAdapter(adapter);
//
//        String movieId = getIntent().getStringExtra("movieId");
//
//        if (movieId != null) {
//            getDetails(movieId);
//        }
//    }
//    public void getDetails(String id) {
//        progressBar.setVisibility(View.VISIBLE);
//
//        ApiInterface apiInterface = RetrofitClient.getRetrofitClient();
//        String[] parts = id.split("/");
//        String titleId = parts[2];
//
//        apiInterface.getDetails(titleId).enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
//
//                if (response.isSuccessful() && response.body() != null) {
//                    MovieResponse movieResponse = response.body();
//                    String title = movieResponse.getTitle();
//                    int year = movieResponse.getYear();
//                    Image image = movieResponse.getImage();
//
//                    // Create a new MovieItem instance for the fetched movie
//                    MovieItem movieItem = new MovieItem(title, year, image);
//                    movieItems.add(movieItem); // Add the movie to the list
//                } else {
//                    movieItems.add(new MovieItem("Unsuccessful response", 401, null));
//                }
//                progressBar.setVisibility(View.GONE);
//                adapter.notifyDataSetChanged(); // Notify the adapter of the data change
//            }
//
//
//
//
//            @Override
//            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
//                movieItems.add(new MovieItem("No movies", 403, null));
//                progressBar.setVisibility(View.GONE);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
//}
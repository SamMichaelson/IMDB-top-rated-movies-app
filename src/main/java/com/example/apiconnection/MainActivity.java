package com.example.apiconnection;

import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MovieAdapter adapter;
    List<MovieItem> movieItems = new ArrayList<>();
    int itemsToShow = 10; // Number of items you want to show
    int currentIndex = 0; // Current index of the movie being fetched

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(movieItems);
        recyclerView.setAdapter(adapter);

        fetchMoviesRecursive();
    }

    private void fetchMoviesRecursive() {
        if (currentIndex < itemsToShow) {
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiInterface = RetrofitClient.getRetrofitClient();
            Call<List<id>> call = apiInterface.getMostPopularMovies();

            call.enqueue(new Callback<List<id>>() {
                @Override
                public void onResponse(@NonNull Call<List<id>> call, @NonNull Response<List<id>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<id> ids = response.body();

                        id responseItem = ids.get(currentIndex);
                        if (isValid(responseItem)) {
                            String id = responseItem.getId();
                            getDetails(id);
                        } else {
                            currentIndex++; // Move to the next index
                            fetchMoviesRecursive(); // Fetch the next movie
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<id>> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // All movies fetched, update the UI
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }

    public void getDetails(String id) {
        ApiInterface apiInterface = RetrofitClient.getRetrofitClient();
        String[] parts = id.split("/");
        String titleId = parts[2];

        apiInterface.getDetails(titleId).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieResponse movieResponse = response.body();
                    String title = movieResponse.getTitle();
                    int year = movieResponse.getYear();
                    Image image = movieResponse.getImage();

                    // Create a new MovieItem instance for the fetched movie
                    MovieItem movieItem = new MovieItem(title, year, image);
                    movieItems.add(movieItem); // Add the movie to the list

                    // Move to the next index and fetch the next movie
                    currentIndex++;
                    fetchMoviesRecursive();
                } else {
                    currentIndex++; // Move to the next index
                    fetchMoviesRecursive(); // Fetch the next movie
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                currentIndex++; // Move to the next index
                fetchMoviesRecursive(); // Fetch the next movie
            }
        });
    }

    private boolean isValid(id responseItem) {
        return responseItem.getId() != null && !responseItem.getId().isEmpty();
    }
}

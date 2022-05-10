package com.example.phim88.views;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.phim88.R;
import com.example.phim88.adapter.ListviewAdapter;
import com.example.phim88.api.ApiGenre;
import com.example.phim88.model.ListGenre;
import com.example.phim88.viewModels.GenresViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    ListView listView;
    Toolbar toolbar;
    List<ListGenre> mList;
    ListviewAdapter listviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolBar);
        listView = findViewById(R.id.lv);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_dehaze_24);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6acafd")));
        listView.setDividerHeight(0);
        GenresViewModel genresViewModel = new GenresViewModel();
        GenresViewModel.Content content = genresViewModel.new Content();
        callApi();
    }

    private void callApi() {
        mList = new ArrayList<>();

        ApiGenre.API_GENRE.genre("c5bc51188f077d87779efbc157e53c08",
                "en-US").enqueue(new Callback<ListGenre>() {
            @Override
            public void onResponse(Call<ListGenre> call, Response<ListGenre> response) {
                ListGenre genres = response.body();

                if (genres.getGenres() != null && genres.getGenres().size() > 0) {
                    for (ListGenre.Content content : genres.getGenres()) {
                        mList.add(genres);
                        listviewAdapter = new ListviewAdapter(mList);
                        listView.setAdapter(listviewAdapter);
                        Log.d("TEST==", "id== " + content.getId() + "====" + content.getName());
                    }
                }
                Log.e(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<ListGenre> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
            case R.id.menu_genres:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_darkMode:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
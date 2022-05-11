package com.example.phim88.view.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.control.rest.Repository;
import com.example.phim88.model.ListGenre;
import com.example.phim88.view.adapter.GenresAdapter;
import com.example.phim88.viewmodel.GenresViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private List<ListGenre> mList;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    GenresAdapter genresAdapter;
    Repository repository = new Repository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.rclv);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_dehaze_24);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6acafd")));

        loadGenres();
    }

    private void loadGenres() {
        mList = new ArrayList<>();
        genresAdapter = new GenresAdapter(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        repository.getGenresListObserver().observe(this, new Observer<ListGenre>() {
            @Override
            public void onChanged(ListGenre listGenre) {
                if (listGenre.getGenres() != null && listGenre.getGenres().size() > 0) {
                    for (ListGenre.Content content : listGenre.getGenres()) {
                        mList.add(listGenre);
                        genresAdapter = new GenresAdapter(mList);
                        recyclerView.setAdapter(genresAdapter);
                        Log.d("TEST==", "id== " + content.getId() + "====" + content.getName());
                    }
                }
                Log.e(TAG, "onChanged: " + listGenre);
            }
        });
        repository.callApi();

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
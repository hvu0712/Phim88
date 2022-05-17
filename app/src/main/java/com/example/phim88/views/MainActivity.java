package com.example.phim88.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.phim88.Adapter.CategoryAdapter;
import com.example.phim88.R;
import com.example.phim88.model.Category;
import com.example.phim88.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvHome;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvHome= findViewById(R.id.rcv_home);
        categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvHome.setLayoutManager(linearLayoutManager);


        categoryAdapter.setData(getListCategory());
        rcvHome.setAdapter(categoryAdapter);
    }

    private List<Category> getListCategory() {

        List<Category> listCategory = new ArrayList<>();

        List<Movie> listMovie = new ArrayList<>();

        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 1 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 2 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 3 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 4 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 5 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 6 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 6 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 7 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 8 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 9 "));
        listMovie.add(new Movie(R.drawable.demo1 , "Phim Hanh Dong 10 "));

        listCategory.add(new Category("Phim hanh dong 1" , listMovie));
        listCategory.add(new Category("Phim hanh dong 2" , listMovie));
        listCategory.add(new Category("Phim hanh dong 3     " , listMovie));

        return listCategory;
    }
}
package com.example.phim88.view.activity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.Resource;
import com.example.phim88.R;
import com.example.phim88.databinding.ActivityMainBinding;
import com.example.phim88.databinding.FragmentMainBinding;
import com.example.phim88.view.adapter.GenresAdapter;
import com.example.phim88.view.fragment.SearchFragment;
import com.example.phim88.viewmodel.GenresViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private GenresViewModel viewModel;

    private int height = 0;
    private int viewHeight;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMainBinding.inflate(inflater, null, false);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_dehaze_24);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6acafd")));

        GenresAdapter adapter = new GenresAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rclv.setLayoutManager(layoutManager);
        binding.rclv.setAdapter(adapter);


        binding.tvAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        viewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        viewModel.getGenres().observe(this, genres -> {
            adapter.setData(genres);
            Log.e(TAG, "onCreate: "+genres);
            adapter.notifyDataSetChanged();
        });
        viewModel.requestGenres();
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
                dropAnim();
                break;
            case R.id.menu_genres:
                binding.navView.openDrawer(GravityCompat.END);
                break;
            case R.id.menu_search:
                SearchFragment fragment = new SearchFragment();
                fragment.setCallback(() -> onBackPressed());
                initFragment(R.id.fragment_container, fragment);
                break;
            case R.id.menu_darkMode:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dropAnim() {
        viewHeight = binding.fragmentMain.getMeasuredHeight();
        if (height == 0 || height < viewHeight) {
            height = binding.fragmentMain.getMeasuredHeight();
        }
        if (height > viewHeight) {
            animator = ValueAnimator.ofInt(viewHeight, height);
        } else {
            animator = ValueAnimator.ofInt(height, viewHeight - 800);
        }
        animator.addUpdateListener(valueAnimator -> {
            int val = (int) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = binding.fragmentMain.getLayoutParams();
            layoutParams.height = val;
            binding.fragmentMain.setLayoutParams(layoutParams);
        });
        animator.setDuration(500);
        animator.start();
    }

    public void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View alert = LayoutInflater.from(this).inflate(R.layout.dialog_app_info, null);
        int width = this.getResources().getDisplayMetrics().widthPixels * 95 / 100;
        builder.create().getWindow().getAttributes().width = width;
        builder.create().getWindow().setGravity(Gravity.CENTER);
        builder.create().getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        builder.create().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.create().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.setView(alert);
        builder.create().show();
    }



}
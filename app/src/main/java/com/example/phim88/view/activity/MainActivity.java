package com.example.phim88.view.activity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.ActivityMainBinding;
import com.example.phim88.view.adapter.GenresAdapter;
import com.example.phim88.view.custom.RippleAnimation;
import com.example.phim88.view.fragment.DetailFragment;
import com.example.phim88.view.fragment.SearchFragment;
import com.example.phim88.viewmodel.GenresViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private GenresViewModel viewModel;

    private int height = 0;
    private int viewHeight;
    private ValueAnimator animator;
    private SharedViewModel sharedViewModel;
    private ImageView imageView;
    private boolean isDark = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMainBinding.inflate(inflater, null, false);
        setContentView(binding.getRoot());
        imageView  = new ImageView(this);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        setSupportActionBar(binding.toolBar);
//        binding.toolBar.inflateMenu(R.menu.menu);
//        binding.toolBar.setTitle("Phim88");
//        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "hohoho", Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case android.R.id.home:
//                        dropAnim();
//                        break;
//                    case R.id.menu_genres:
//                        binding.navView.openDrawer(GravityCompat.END);
//                        break;
//                    case R.id.menu_search:
//                        SearchFragment fragment = new SearchFragment();
//                        fragment.setCallback(() -> onBackPressed());
//                        initFragment(R.id.fragment_container, fragment);
//                        break;
//                    case R.id.menu_darkMode:
//                        initTheme();
//                        break;
//                }
//                return false;
//            }
//        });
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_dehaze_24);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6acafd")));

        GenresAdapter adapter = new GenresAdapter(getBaseContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rclv.setLayoutManager(layoutManager);
        binding.rclv.setAdapter(adapter);

        binding.tvAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });


        viewModel.getGenres().observe(this, genres -> {
            adapter.setData(genres);
            adapter.notifyDataSetChanged();
        });
        viewModel.requestGenres();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
                initTheme();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTheme(){
        if (isDark == true){
            RippleAnimation.create(imageView).setDuration(500).start();
            binding.toolBar.setBackgroundColor(Color.GREEN);
            binding.toolBar.setTitleTextColor(Color.BLACK);
            binding.rclv.setBackgroundColor(Color.BLACK);
//            TextView textView = findViewById(R.id.tv_genre);
//            textView.setTextColor(Color.WHITE);
            ConstraintLayout constraintLayout = findViewById(R.id.main);
            constraintLayout.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.main_shape_black));
            isDark = false;
        } else {
            isDark = true;
            RippleAnimation.create(imageView).setDuration(500).start();
            binding.toolBar.setBackgroundColor(0xff6acafd);
            binding.toolBar.setTitleTextColor(Color.WHITE);
            binding.rclv.setBackgroundColor(Color.WHITE);
            ConstraintLayout constraintLayout = findViewById(R.id.main);
            constraintLayout.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.main_shape));
//            TextView textView = findViewById(R.id.tv_genre);
//            textView.setTextColor(Color.BLACK);
        }
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

    public void createDialog() {
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

    interface DarkModeInterFace{
        void enableDarkMode();
    }

}
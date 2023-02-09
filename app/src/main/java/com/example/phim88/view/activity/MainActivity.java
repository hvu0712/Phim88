package com.example.phim88.view.activity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.ActivityMainBinding;
import com.example.phim88.model.Category;
import com.example.phim88.view.adapter.CategoryAdapter;
import com.example.phim88.view.adapter.GenresAdapter;
import com.example.phim88.view.custom.RippleAnimation;
import com.example.phim88.view.custom.Theme;
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
    public boolean isDark = true;
    private DarkModeInterFace darkModeInterFace;
    private CategoryAdapter categoryAdapter;
    private Category category;
    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Log.e(TAG, "onAvailable: ");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    GenresAdapter adapter = new GenresAdapter(getBaseContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    binding.rclv.setLayoutManager(layoutManager);
                    binding.rclv.setAdapter(adapter);
                    viewModel.getGenres().observe(MainActivity.this, genres -> {
                        adapter.setData(genres);
                        adapter.notifyDataSetChanged();
                    });
                    viewModel.requestGenres();
                }
            });
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Log.e(TAG, "onLost: ");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
            final boolean unmetered1 = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            Log.e(TAG, "onCapabilitiesChanged: "+unmetered+ " "+unmetered1);
        }
    };

    public void setDarkModeInterFace(DarkModeInterFace darkModeInterFace) {
        this.darkModeInterFace = darkModeInterFace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMainBinding.inflate(inflater, null, false);
        setContentView(binding.getRoot());
        categoryAdapter = new CategoryAdapter();
        imageView = new ImageView(this);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        setSupportActionBar(binding.toolBar);


        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);
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


    private void initTheme() {
        if (isDark == true) {
            RippleAnimation.create(imageView).setDuration(500).start();
            binding.toolBar.setBackgroundColor(Theme.key_backGroundMainDark);
            binding.toolBar.setTitleTextColor(Theme.key_white_dark);
            binding.rclv.setBackgroundColor(Theme.key_white_dark);
            TextView textView = findViewById(R.id.tv_genre);
            TextView tvMore = findViewById(R.id.btn_more);
            tvMore.setBackgroundColor(Color.BLACK);
            textView.setTextColor(Color.WHITE);
            synchronized (categoryAdapter){
                categoryAdapter.setDark(true);
                categoryAdapter.notify();
                categoryAdapter.notifyDataSetChanged();
            }
            categoryAdapter.setDark(true);
            categoryAdapter.notifyDataSetChanged();
            ConstraintLayout constraintLayout = findViewById(R.id.main);
            constraintLayout.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.main_shape_black));
            FragmentContainerView fragmentContainerView = findViewById(R.id.fragment_main);
            fragmentContainerView.setBackgroundColor(Theme.key_backGroundMainDark);
            isDark = false;
        } else {
            isDark = true;
            RippleAnimation.create(imageView).setDuration(500).start();
            binding.toolBar.setBackgroundColor(Theme.key_backGroundMain);
            binding.toolBar.setTitleTextColor(Theme.key_white);
            binding.rclv.setBackgroundColor(Theme.key_white);
            ConstraintLayout constraintLayout = findViewById(R.id.main);
            constraintLayout.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.main_shape));
            TextView textView = findViewById(R.id.tv_genre);
            FragmentContainerView fragmentContainerView = findViewById(R.id.fragment_main);
            fragmentContainerView.setBackgroundColor(Theme.key_backGroundMain);
            textView.setTextColor(Color.BLACK);
            TextView tvMore = findViewById(R.id.btn_more);
            tvMore.setBackgroundColor(Theme.key_white);
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

    public interface DarkModeInterFace {
        void enableDarkMode();
    }

}
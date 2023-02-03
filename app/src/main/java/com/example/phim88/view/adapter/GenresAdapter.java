package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.control.ItemClickListener;
import com.example.phim88.databinding.LvItemBinding;
import com.example.phim88.model.genre.Genre;
import com.example.phim88.view.fragment.GenresFragment;
import com.example.phim88.viewmodel.GenresViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private List<Genre> data;
    private SharedViewModel sharedViewModel;
    private Context context;
    private GenresViewModel genresViewModel;

    public GenresAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }


    public void setData(List<Genre> data) {
        if (data == null) return;
        this.data.clear();
        this.data.addAll(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        LvItemBinding binding = LvItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre item = data.get(position);
        if (item == null) {
            return;
        }
        GenresFragment genresFragment = new GenresFragment();
        holder.binding.tvItem.setText(item.getName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
//                    chưa có ý tưởng cho longClick, bao giờ có thêm sau
//                    genresFragment.setGetData(() -> item.getName());
//                    genresFragment.setGetId(() -> item.getId());
//                    initFragment(genresFragment, view);
//                    Toast.makeText(context,"Long click: "+ item.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    genresFragment.setGetData(() -> item.getName());
                    genresFragment.setGetId(() -> item.getId());
                    initFragment(genresFragment, view);
                }
            }
        });
    }

    private void initFragment(Fragment fragment, View view){
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public LvItemBinding binding;
        private ItemClickListener itemClickListener;

        public ViewHolder(LvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.getRoot().setOnClickListener(this);
            this.binding.getRoot().setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }

}

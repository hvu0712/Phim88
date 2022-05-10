package com.example.phim88.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.phim88.R;
import com.example.phim88.model.ListGenre;

import java.util.ArrayList;
import java.util.List;

public class ListviewAdapter extends BaseAdapter {
    List<ListGenre> mList;

    public ListviewAdapter(List<ListGenre> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lv_item, viewGroup, false);
        TextView textView = view1.findViewById(R.id.tv_item);
        ListGenre genres = mList.get(i);
        textView.setText(String.valueOf(genres.getGenres().get(i).getName()));
        return view1;
    }
}

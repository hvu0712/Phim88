package com.example.phim88.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phim88.BR;
import com.example.phim88.control.api.ApiGenre;
import com.example.phim88.control.rest.request.MyRequest;
import com.example.phim88.model.ListGenre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresViewModel extends ViewModel {
    private static final String TAG = "GenresViewModel";


    public class Content extends BaseObservable {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Bindable
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }
    }
}

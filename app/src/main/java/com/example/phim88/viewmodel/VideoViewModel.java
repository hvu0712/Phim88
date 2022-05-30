package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.Video.Video;
import com.example.phim88.model.Video.VideoResponse;

import java.util.ArrayList;
import java.util.List;

public class VideoViewModel extends BaseViewModel{

    private MutableLiveData<List<Video>> listVideo;
    private MutableLiveData<Integer> id = new MutableLiveData<>();

    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    public void setId(Integer data) {
        id.postValue(data);
    }

    public LiveData<Integer> getSelectedId(){
        return id;
    }

    public MutableLiveData<List<Video>> getListVideo() {
        if (listVideo == null){
            listVideo = new MutableLiveData<>();
            listVideo.setValue(new ArrayList<>());
        }
        return listVideo;
    }

    public void requestVideo(int movie_id){
        repository.callVideo(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof VideoResponse){
                    List<Video> videoList = ((VideoResponse) object).getResults();
                    if (videoList != null && videoList.size() > 0){
                        VideoViewModel.this.listVideo.postValue(videoList);
                    }
                }
            }

            @Override
            public void fail(String msg) {

            }
        }, movie_id);
    }
}

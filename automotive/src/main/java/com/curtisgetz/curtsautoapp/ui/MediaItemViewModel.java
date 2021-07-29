//package com.example.curtsautoapp.ui;
//
//import android.database.sqlite.SQLiteConstraintException;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.Transformations;
//import androidx.lifecycle.ViewModel;
//
//import com.example.curtsautoapp.model.MediaItem;
//import com.example.curtsautoapp.model.Podcast;
//import com.example.curtsautoapp.network.APIBase;
//import com.example.curtsautoapp.network.ListenNotesAPI;
//
//public class MediaItemViewModel extends ViewModel {
//
//    private APIBase api;
//    public MutableLiveData<Podcast> podcastLiveData = new MutableLiveData<>();
//    private final MutableLiveData<MediaItem> mediaItemLiveData = new MutableLiveData<>();
//    public final LiveData<MediaItem> mediaItem =
//            Transformations.switchMap(mediaItemLiveData, (item) -> {
//                return api.
//            })
//
//    MediaItemViewModel (){
//        this.api = ListenNotesAPI.getInstance();
//    }
//
//    public LiveData<Podcast> getPodcast(){
//
//    }
//
//    public LiveData<MediaItem> getMediaItem(){
//
//    }
//
//
//
//}

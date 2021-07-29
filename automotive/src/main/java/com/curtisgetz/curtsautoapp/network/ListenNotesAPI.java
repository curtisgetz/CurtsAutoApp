package com.curtisgetz.curtsautoapp.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.curtisgetz.curtsautoapp.model.Episode;
import com.curtisgetz.curtsautoapp.model.Genre;
import com.curtisgetz.curtsautoapp.model.GenresResponse;
import com.curtisgetz.curtsautoapp.model.ListenNotesResponse;
import com.curtisgetz.curtsautoapp.model.MediaItem;
import com.curtisgetz.curtsautoapp.model.Podcast;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class ListenNotesAPI extends APIBase{

    private String BASE_URL = "https://listen-api.listennotes.com/api/v2";
    //private String GENRE_ID = "127";
    private ListenNotesResponse listenNotesResponse;
    private MediaItem mediaItem;
    private Podcast podcast;
    public MutableLiveData<Podcast> podcastLiveData = new MutableLiveData<>();
    public MutableLiveData<MediaItem> mediaItemLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Genre>> genresLiveData = new MutableLiveData<>();
    private static ListenNotesAPI sInstance;

    public static ListenNotesAPI getInstance() {
        if (sInstance == null){
            sInstance = new ListenNotesAPI();
        }
        return sInstance;
    }

    public void initDownload(String genreId){
        try {
            //Client objClient = new Client("f4601a839e0f4a568e7bca504cb2bd51");
            HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
            urlBuilder.addPathSegment("best_podcasts");
            urlBuilder.addQueryParameter("genre_id", genreId);
            urlBuilder.addQueryParameter("page", "1");
            urlBuilder.addQueryParameter("region", "us");
            urlBuilder.addQueryParameter("safe_mode", "0");
            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .header("X-ListenAPI-Key", "f4601a839e0f4a568e7bca504cb2bd51")
                    .url(url)
                    .build();
            //request = new Request.Builder().header("X-ListenAPI-Key", "f4601a839e0f4a568e7bca504cb2bd51").url("https://listen-api.listennotes.com/api/v2/genres?top_level_only=1").build();
            Log.d("REQUEST", request.toString());
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    //Log.d("JSON", response.body().string());
                    try{
                        String responseData = response.body().string();
                        Log.d("RESPONSE", responseData);
                        //JSONObject json = new JSONObject(responseData);
                        //JSONObject o = json;
                        Gson gson = new Gson();
                        listenNotesResponse = gson.fromJson(responseData, ListenNotesResponse.class);
                        Log.d("TEST", "TEST");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        getRandomPodcast();
    }

    public void getRandomPodcast(){
        if (listenNotesResponse == null || listenNotesResponse.getPodcasts() == null) return;
        List<Podcast> podcasts = listenNotesResponse.getPodcasts();
        int podcastIndex = (int) (Math.random() * podcasts.size());
        Podcast selectedPodcast = podcasts.get(podcastIndex);
        podcastLiveData.postValue(selectedPodcast);

    }

    public void getRandomEpisode(){
        if (podcastLiveData.getValue() == null) return;
        String podcast_id = podcastLiveData.getValue().getId();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addPathSegment("podcasts");
        urlBuilder.addPathSegment(podcast_id);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .header("X-ListenAPI-Key", "f4601a839e0f4a568e7bca504cb2bd51")
                .url(url)
                .build();
        Log.d("EPISODE", request.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                //podcastLiveData.postValue(gson.fromJson(responseData, Podcast.class));
                Podcast tempPodcast = (gson.fromJson(responseData, Podcast.class));
                Log.d("TEST", "TEST");
                List<Episode> episodes = tempPodcast.getEpisodes();
                int episodeIndex = (int) (Math.random() * episodes.size());
                Episode episode = episodes.get(episodeIndex);
                MediaItem mediaItem = new MediaItem(episode.getUrl(), episode.getTitle(), episode.getId(), tempPodcast.getTitle(), tempPodcast.getId());
                mediaItemLiveData.postValue(mediaItem);
            }
        });

    }

    public void getGenres(){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addPathSegment("genres");
        urlBuilder.addQueryParameter("top_level_only", "1");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .header("X-ListenAPI-Key", "f4601a839e0f4a568e7bca504cb2bd51")
                .url(url)
                .build();
        Log.d("GENRE", request.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                //podcastLiveData.postValue(gson.fromJson(responseData, Podcast.class));
                GenresResponse genresResponse = (gson.fromJson(responseData, GenresResponse.class));
                List<Genre> genres = genresResponse.getGenres();
                genresLiveData.postValue(genres);
            }
        });

    }

    public void clearData(){
        podcastLiveData = new MutableLiveData<>();
        mediaItemLiveData = new MutableLiveData<>();
    }


}

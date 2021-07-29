package com.curtisgetz.curtsautoapp.network;

import com.curtisgetz.curtsautoapp.model.Genre;
import com.curtisgetz.curtsautoapp.model.GenresResponse;
import com.curtisgetz.curtsautoapp.model.ListenNotesResponse;
import com.curtisgetz.curtsautoapp.model.Podcast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListenNotesRetrofit {

    String BASE_URL = "https://listen-api.listennotes.com/api/v2/";

    @GET("best_podcasts/")
    Observable<ListenNotesResponse> getListenNotes (@Header("X-ListenAPI-Key") String token,
            @Query("genre_id") String genre_id,
            @Query("page") String page,
            @Query("region") String region,
            @Query("safe_mode") String safeMode);

    @GET("genres?top_level_only=1")
    Observable<GenresResponse> getGenres (@Header("X-ListenAPI-Key") String token);

    @GET("podcasts/{podcast_id}")
    Observable<Podcast> getPodcastEpisodes(@Header("X-ListenAPI-Key") String token,
                                       @Path("podcast_id") String podcastId);

}

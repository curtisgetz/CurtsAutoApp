package com.curtisgetz.curtsautoapp.model;

public class MediaItem {

    private String url;
    private String title;
    private String id;
    private String podcastName;
    private String podcastId;

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    public MediaItem(String url, String title, String id){
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public MediaItem(String url, String title, String id, String podcastName, String podcastId) {
        this.url = url;
        this.title = title;
        this.id = id;
        this.podcastName = podcastName;
        this.podcastId = podcastId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

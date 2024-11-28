package com.macosso.youtube_video_downloader.model;

public class DownloadRequest {
    private String url;
    private String videoName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

}

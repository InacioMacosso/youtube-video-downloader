package com.macosso.youtube_video_downloader.controller;

import com.macosso.youtube_video_downloader.model.DownloadRequest;
import com.macosso.youtube_video_downloader.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @PostMapping
    public String downloadVideo(@RequestBody DownloadRequest request) {
        return downloadService.downloadAndConvertToMp4(request.getUrl(), request.getVideoName());
    }
}

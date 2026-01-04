package com.Music.App.controller;

import com.Music.App.model.Album;
import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/albums")

public class AlbumController {
    private final AlbumService albumService;
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album){
        return ResponseEntity.ok(albumService.saveAlbum(album));
    }
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(){
        return ResponseEntity.ok(albumService.getAllAlbums());
    }
}

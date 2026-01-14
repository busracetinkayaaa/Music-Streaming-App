package com.Music.App.controller;

import com.Music.App.exception.ResourceNotFoundException;
import com.Music.App.model.Album;
import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Music.App.repository.ArtistRepo;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/albums")

public class AlbumController {
    private final AlbumService albumService;
    private final ArtistRepo artistRepo;
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album){
        if(album.getArtist()!=null &&album.getArtist().getId()!=null){
            Artist existingArtist= artistRepo.findById(album.getArtist().getId()).orElseThrow(()-> new ResourceNotFoundException("Artist not found."));
            album.setArtist(existingArtist);
        }
        return ResponseEntity.ok(albumService.saveAlbum(album));
    }
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(){
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Album>> getAlbumByTitle(@RequestParam String title){
        return ResponseEntity.ok(albumService.getAlbumByTitle(title));
    }
    @GetMapping("/artist/{artist_id}")
    public ResponseEntity<List<Album>> getAlbumByArtistId(@PathVariable Long artist_id){
        return ResponseEntity.ok(albumService.getAlbumByArtistId(artist_id));
    }
    @GetMapping("/release year")
    public ResponseEntity<List<Album>> getAlbumByReleaseYear(@RequestParam int releaseYear){
        return ResponseEntity.ok(albumService.getAlbumByReleaseYear(releaseYear));
    }
    @GetMapping("/Recent albums by artist")
    public ResponseEntity<List<Album>> getRecentAlbumsByArtist(@RequestParam Long artist_id,@RequestParam int year){
        return ResponseEntity.ok(albumService.getRecentAlbumsByArtist(artist_id,year));
    }
}

package com.Music.App.controller;

import com.Music.App.model.Album;
import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.service.ArtistService;
import com.Music.App.service.SongService;
import io.swagger.v3.oas.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artists")
@Slf4j
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist){
        return ResponseEntity.ok(artistService.saveArtist(artist));
    }
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists(){
        return ResponseEntity.ok(artistService.getAllArtists());
    }
}


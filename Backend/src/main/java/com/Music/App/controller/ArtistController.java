package com.Music.App.controller;

import com.Music.App.model.Artist;
import com.Music.App.service.ArtistService;
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

    @GetMapping("/name")
    public ResponseEntity<Optional<Artist>> getArtistByName(@RequestParam("name") String name){
        return ResponseEntity.ok(artistService.getArtistByName(name));
    }
    @GetMapping("/genre")
    public ResponseEntity<List<Artist>> getArtistByGenre(@RequestParam("genre") String genre){
        return ResponseEntity.ok(artistService.getArtistByGenre(genre));
    }
    @GetMapping("/productive artists")
    public ResponseEntity<List<Artist>> getProductiveArtists(@RequestParam("songCount") int songCount){
        return ResponseEntity.ok(artistService.getProductiveArtists(songCount));
    }


}


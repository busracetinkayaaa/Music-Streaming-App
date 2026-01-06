package com.Music.App.controller;

import com.Music.App.dto.SongRequestDTO;
import com.Music.App.dto.SongResponseDTO;
import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.service.SongService;
import io.swagger.v3.oas.annotations.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
@Slf4j
@Valid
public class SongController {
    private final SongService songService;
    @PostMapping
    public ResponseEntity<SongResponseDTO> createSong(@Valid @RequestBody SongRequestDTO dto){
        log.info("API: Yeni şarkı oluşturma isteği - {}", dto.getTitle());
        return ResponseEntity.ok(songService.saveSong(dto));
    }
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs(){
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/album/{album_id}")
    public ResponseEntity<List<Song>> getByAlbum(@PathVariable Long album_id) {
        return ResponseEntity.ok(songService.getAllSongsByAlbum(album_id));
    }
    @GetMapping("/search")
    public ResponseEntity<Song> searchByTitle(@RequestParam String title) {
        return songService.searchSongByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/longCollabrations")
    public ResponseEntity<List<Song>> getLongCollaborations(
            @Parameter(description = "Minimum saniye süresi") @RequestParam int minDuration) {
        log.info("API: {} saniyeden uzun işbirlikleri isteniyor.", minDuration);
        return ResponseEntity.ok(songService.getLongCollabrations(minDuration));
    }
    @GetMapping("/artist")
    public ResponseEntity<List<Song>> getByArtist(@RequestBody Artist artist){
        return ResponseEntity.ok(songService.getSongsByArtist(artist));
    }
    @DeleteMapping("/{song_id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long song_id){
        songService.deleteSong(song_id);
        return ResponseEntity.ok("Şarkı silindi.");
    }
}

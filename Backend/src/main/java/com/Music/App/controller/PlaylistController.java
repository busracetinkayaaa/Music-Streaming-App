package com.Music.App.controller;

import com.Music.App.model.Playlist;
import com.Music.App.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    @PostMapping
    public ResponseEntity<Playlist> createPlaylists(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.savePlaylist(playlist));
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Playlist>> getAllPlaylist(@PathVariable("name") String name){
        return ResponseEntity.ok(playlistService.getPlaylistByName(name));
    }
    @GetMapping("/song/{song_id}")
    public ResponseEntity<List<Playlist>> getAllPlaylistsContainingSong(@PathVariable("song_id") Long song_id){
        return ResponseEntity.ok(playlistService.getAllPlaylistsContainingSong(song_id));
    }
}

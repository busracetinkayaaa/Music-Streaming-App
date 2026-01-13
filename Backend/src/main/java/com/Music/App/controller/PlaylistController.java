package com.Music.App.controller;

import com.Music.App.dto.PlaylistResponseDTO;
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
    @GetMapping
    public ResponseEntity<List<Playlist>> getPlaylists(){
        return  ResponseEntity.ok(playlistService.getAllPlaylists());
    }
    @PutMapping("/{playlist_id}/add-song/{song_id}")
    public ResponseEntity<PlaylistResponseDTO> addSongToPlaylist(
            @PathVariable Long playlist_id,
            @PathVariable Long song_id) {
        PlaylistResponseDTO response=playlistService.addSongToPlaylist(playlist_id,song_id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/name")
    public ResponseEntity<List<Playlist>> getAllPlaylist(@RequestParam("name") String name){
        return ResponseEntity.ok(playlistService.getPlaylistByName(name));
    }
    @GetMapping("/song/{song_id}")
    public ResponseEntity<List<Playlist>> getAllPlaylistsContainingSong(@PathVariable("song_id") Long song_id){
        return ResponseEntity.ok(playlistService.getAllPlaylistsContainingSong(song_id));
    }
    @DeleteMapping("/{playlist_id}/delete-song/{song_id}")
    public ResponseEntity<Playlist> deleteSongFromPlaylist(@PathVariable Long playlist_id,Long song_id){
        return ResponseEntity.ok(playlistService.deleteSongFromPlaylist(song_id,playlist_id));
    }

    @DeleteMapping("/{playlist_id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long playlist_id){
        playlistService.deletePlaylist(playlist_id);
        return ResponseEntity.ok("silindi");
    }
    @GetMapping("/details/{id}")
    public PlaylistResponseDTO getPlaylistDetails(@PathVariable Long id){

        return playlistService.getPlaylistDetails(id);
    }
}

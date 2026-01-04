package com.Music.App.service;

import com.Music.App.model.Artist;
import com.Music.App.model.Playlist;
import com.Music.App.repository.PlaylistRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistService {
    public PlaylistRepo playlistRepo;

    public List<Playlist> getPlaylistByName(String name){
        return playlistRepo.findByName(name);
    }
    public List<Playlist> getUserByID(Long user_id){
        return playlistRepo.findByUserId(user_id);

    }
    public List<Playlist> getAllPlaylistsContainingSong(Long song_id){
        log.info("Şarkı (ID: {}) içeren tüm çalma listeleri sorgulanıyor.", song_id);
        List<Playlist> playlist= playlistRepo.findAllPlaylistsContainingSong(song_id);
        log.info("{} adet çalma listesinde bu şarkı bulundu.", playlist.size());
        return playlist;
    }
    @Transactional
    public Playlist savePlaylist(Playlist playlist){
        return playlistRepo.save(playlist);
    }

}

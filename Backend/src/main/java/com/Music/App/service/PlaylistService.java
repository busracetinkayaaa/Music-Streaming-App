package com.Music.App.service;

import com.Music.App.dto.PlaylistResponseDTO;
import com.Music.App.exception.ResourceNotFoundException;
import com.Music.App.model.Artist;
import com.Music.App.model.Playlist;
import com.Music.App.model.Song;
import com.Music.App.repository.PlaylistRepo;
import com.Music.App.repository.SongRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final SongRepo songRepo;
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
    @Transactional
    public PlaylistResponseDTO addSongToPlaylist(Long playlist_id,Long song_id){
        log.info("Gelen Playlist ID: {}, Gelen Song ID: {}", playlist_id, song_id);
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException("Playlist Bulunamadı"));
        Song song= songRepo.findById(song_id).orElseThrow(()->new ResourceNotFoundException("Şarkı bulunamadı"));

        if(!playlist.getSongs().contains(song)){
            playlist.getSongs().add(song);
        }

        Playlist savedPlaylist= playlistRepo.save(playlist);
        PlaylistResponseDTO response= new PlaylistResponseDTO();
        response.setId(savedPlaylist.getId());
        response.setName(savedPlaylist.getName());

        List<String> titles=savedPlaylist.getSongs().stream().map(Song::getTitle).toList();
        response.setSongTitles(titles);
        return response;
    }

    @Transactional
    public Playlist deleteSongFromPlaylist(Long song_id,Long playlist_id){
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException("playlist yok"));
        Song song=songRepo.findById(song_id).orElseThrow(()->new ResourceNotFoundException("zaten şarkı yok"));
        if(playlist.getSongs().contains(song)){
            playlist.getSongs().remove(song);
        }
        return playlistRepo.save(playlist);
    }

    public void deletePlaylist(Long playlist_id){
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->  new RuntimeException("playlist bulunamadı"));

        playlistRepo.delete(playlist);

    }

}

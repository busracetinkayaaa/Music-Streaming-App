package com.Music.App.service;

import com.Music.App.dto.PlaylistResponseDTO;
import com.Music.App.dto.SongResponseDTO;
import com.Music.App.exception.GlobalExceptionHandler;
import com.Music.App.exception.ResourceNotFoundException;
import com.Music.App.model.Artist;
import com.Music.App.model.Playlist;
import com.Music.App.model.Song;
import com.Music.App.repository.PlaylistRepo;
import com.Music.App.repository.SongRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final SongRepo songRepo;

    @Transactional
    public Playlist savePlaylist(Playlist playlist){
        return playlistRepo.save(playlist);
    }

    public List<Playlist> getAllPlaylists(){return playlistRepo.findAll();}
    @Transactional
    public PlaylistResponseDTO addSongToPlaylist(Long playlist_id,Long song_id){
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException("Playlist Bulunamadı"));
        Song song= songRepo.findById(song_id).orElseThrow(()->new ResourceNotFoundException("Şarkı bulunamadı"));

        if (playlist.getSongs().contains(song)) {
            throw new DataIntegrityViolationException("Bu şarkı zaten playlist'te mevcut.");
        }

        playlist.getSongs().add(song);

        Playlist savedPlaylist= playlistRepo.save(playlist);
        PlaylistResponseDTO response= new PlaylistResponseDTO();
        response.setId(savedPlaylist.getId());
        response.setName(savedPlaylist.getName());

        List<SongResponseDTO> songDto= savedPlaylist.getSongs().stream().map(s->{
            SongResponseDTO dto=new SongResponseDTO();
            dto.setId(s.getId());;
            dto.setTitle(s.getTitle());
            dto.setImageUrl(s.getImageUrl());
            if(s.getArtist()!=null){
                dto.setArtistName(s.getArtist().getName());
            }
            return dto;
        })
                .toList();
        response.setSongs(songDto);
        int totalDuration=playlist.getSongs().stream().mapToInt(Song::getDuration).sum();
        response.setTotalDuration(totalDuration);
        return response;
    }

    public PlaylistResponseDTO getPlaylistDetails(Long id){
        Playlist playlist=playlistRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("playlist bulunamadı."));

        int totalDuration=playlist.getSongs().stream().mapToInt(Song::getDuration).sum();

        PlaylistResponseDTO response= new PlaylistResponseDTO();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setTotalDuration(totalDuration);
        response.setFormattedDuration(formatDuration(totalDuration));

        response.setSongs(playlist.getSongs().stream().map(song -> {
            SongResponseDTO sDto = new SongResponseDTO();
            sDto.setId(song.getId());
            sDto.setTitle(song.getTitle());
            sDto.setDuration(song.getDuration());
            sDto.setArtistName(song.getArtist().getName());
            sDto.setImageUrl(song.getImageUrl());
            return sDto;
        }).collect(Collectors.toList()));

        return response;

    }
    private String formatDuration(int duration){
    int hours= duration/3600;
    int minutes=(duration%3600)/60;
    int seconds=duration%60;

    if(hours>0) {
        return String.format("%dh %dm %ds", hours,minutes, seconds);
    }
    return String.format("%dm %ds",minutes, seconds);

    }

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
    public Playlist deleteSongFromPlaylist(Long song_id,Long playlist_id){
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException("playlist yok"));
        Song song=songRepo.findById(song_id).orElseThrow(()->new ResourceNotFoundException("zaten şarkı yok"));
        if(playlist.getSongs().contains(song)){
            playlist.getSongs().remove(song);
        }
        return playlistRepo.save(playlist);
    }

    public void deletePlaylist(Long playlist_id){
        Playlist playlist=playlistRepo.findById(playlist_id).orElseThrow(()->  new ResourceNotFoundException("playlist bulunamadı"));

        playlistRepo.delete(playlist);

    }

}

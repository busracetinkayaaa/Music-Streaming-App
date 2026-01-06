package com.Music.App.service;

import com.Music.App.dto.SongRequestDTO;
import com.Music.App.dto.SongResponseDTO;
import com.Music.App.exception.ResourceNotFoundException;
import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.repository.SongRepo;
import com.Music.App.repository.ArtistRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private final SongRepo songRepo;
    private final ArtistRepo artistRepo;
    @Transactional
    public SongResponseDTO saveSong(SongRequestDTO dto){
        Song song=new Song();
        song.setTitle(dto.getTitle());
        song.setDuration(dto.getDuration());
        song.setImageUrl(dto.getImageUrl());

        if(dto.getArtist_id()!= null){
            Artist mainArtist= artistRepo.findById(dto.getArtist_id()).orElseThrow(()-> new ResourceNotFoundException("ID'si " + dto.getArtist_id() + " olan sanatçı sistemde bulunamadı."));
            song.setArtist(mainArtist);
            song.getPerformers().add(mainArtist);
        }
        Song savedSong=songRepo.save(song);
        SongResponseDTO response=new SongResponseDTO();
        response.setId(savedSong.getId());
        response.setTitle(savedSong.getTitle());
        response.setDuration(savedSong.getDuration());
        response.setImageUrl(savedSong.getImageUrl());

        if(savedSong.getArtist()!=null){
            response.setArtistName(savedSong.getArtist().getName());
        }

        log.info("Şarkı veritabanına kaydediliyor: {}", song.getTitle());
        return response;
    }
    public List<Song> getAllSongs(){
        log.debug("Tüm şarkılar listeleniyor.");
        return songRepo.findAll();
    }

    public Optional<Song> searchSongByTitle(String title){
      log.info(title+"adlı şarkı aranıyor ");
        return songRepo.findByTitleContainingIgnoreCase(title);
    }

    public List<Song> getAllSongsByAlbum(Long album_id){
        log.info(album_id+"Albümdeki tüm şarkılar listeleniyor:");
        return songRepo.findByAlbumId(album_id);
    }
    public List<Song> getSongsByArtist(Artist artist){
        log.info(artist.getName()+"Sanatçının şarkıları listeleniyor.");
        return songRepo.findByPerformersContaining(artist);
    }
    public List<Song> getLongCollabrations(int minDuration){
        log.info(minDuration+"saniyeden uzun birden fazla sanatçılı şarkılar sorgulanıyor.");
        List<Song> songs= songRepo.findLongCollabrations(minDuration);
        log.info(songs.size()+"tane uzun işbirliği bulundu.");
        return songs;
    }







}

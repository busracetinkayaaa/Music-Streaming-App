package com.Music.App.service;

import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import com.Music.App.repository.SongRepo;
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
    @Transactional
    public Song saveSong(Song song){
        log.info("Şarkı veritabanına kaydediliyor: {}", song.getTitle());
        return songRepo.save(song);
    }





}

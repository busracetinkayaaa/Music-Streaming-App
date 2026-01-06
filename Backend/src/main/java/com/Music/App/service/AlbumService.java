package com.Music.App.service;

import com.Music.App.model.Album;
import com.Music.App.model.Artist;
import com.Music.App.repository.AlbumRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumService {
    private final AlbumRepo albumRepo;

    public List<Album> getAlbumByTitle(String title){
        log.info(title+"adlı albüm aranıyor.");
        return albumRepo.findByTitle(title);
    }
    public List<Album> getAlbumByArtistId(Long artist_id){
        log.info("Sanatçıya ait albümler getiriliyor. Sanatçı ID: {}", artist_id);
        return albumRepo.findByArtistId(artist_id);
    }
    public List<Album> getAlbumByReleaseYear(int releaseYear){
        log.info("{} yılına ait albümler listeleniyor.", releaseYear);
        return albumRepo.findByReleaseYear(releaseYear);
    }

    public List<Album> getRecentAlbumsByArtist(Long artist_id,int year){
        log.info("Sanatçının (ID: {}) {} yılından sonraki güncel albümleri getiriliyor.", artist_id, year);
        List<Album> recentAlbums= albumRepo.findRecentAlbumByArtist(artist_id,year);
        log.info("{} adet güncel albüm bulundu.", recentAlbums.size());
        return recentAlbums;
    }
    @Transactional
    public Album saveAlbum(Album album){
        log.info("Albüm veritabanına kaydediliyor: {}", album.getTitle());
        return albumRepo.save(album);
    }
    public List<Album> getAllAlbums(){
        log.debug("Tüm albümler listeleniyor.");
        return albumRepo.findAll();
    }


}

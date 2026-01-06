package com.Music.App.service;

import com.Music.App.model.Artist;
import com.Music.App.repository.AlbumRepo;
import com.Music.App.repository.ArtistRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class ArtistService {
    private final ArtistRepo artistRepo;

    public Optional<Artist> getArtistByName(String name){
        return artistRepo.findByNameContainingIgnoreCase(name);
    }
    public List<Artist> getArtistByGenre(String genre){
        return artistRepo.findByGenre(genre);
    }
    public List<Artist> getProductiveArtists(int songCount){
        log.info("{} adetten fazla şarkısı olan üretken sanatçılar listeleniyor.", songCount);
        return artistRepo.findProductiveArtists(songCount);
    }
    @Transactional
    public Artist saveArtist(Artist artist){
        log.info("Yeni sanatçı kaydediliyor: {}", artist.getName());
        return artistRepo.save(artist);
    }
    public List<Artist> getAllArtists(){
        log.debug("Tüm sanatçılar listeleniyor.");
        return artistRepo.findAll();
    }


}

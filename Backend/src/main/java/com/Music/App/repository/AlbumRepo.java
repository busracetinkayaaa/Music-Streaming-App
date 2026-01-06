package com.Music.App.repository;

import com.Music.App.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Long> {
    List<Album> findByArtistId(Long artist_id);
    List<Album> findByTitle(String title);
    List<Album> findByReleaseYear(int releaseYear);
    @Query("SELECT a FROM Album a WHERE a.artist.id = :artistId AND a.releaseYear > :year")
    List<Album> findRecentAlbumByArtist(@Param("artist_id") Long artist_id,@Param("year") int year);



}

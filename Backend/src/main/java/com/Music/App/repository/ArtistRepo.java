package com.Music.App.repository;

import com.Music.App.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist,Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String name);
    List<Artist> findByGenre(String genre);
    @Query("SELECT a FROM Artist a WHERE size(a.songs)> :songCount")
    List<Artist> findProductiveArtists(@Param("songCount") int songCount);
}

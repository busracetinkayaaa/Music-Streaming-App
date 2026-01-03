package com.Music.App.repository;

import com.Music.App.model.Artist;
import com.Music.App.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepo extends JpaRepository<Song,Long> {
    Optional<Song> findByTitleContainingIgnoreCase(String title);
    List<Song> findByAlbumId(Long album_id);
    List<Song> findByPerformersContaining(Artist artist);

}

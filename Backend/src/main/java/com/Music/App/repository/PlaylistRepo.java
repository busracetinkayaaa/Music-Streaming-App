package com.Music.App.repository;

import com.Music.App.model.Playlist;
import com.Music.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PlaylistRepo extends JpaRepository<Playlist,Long> {
    List<Playlist> findByName(String name);
    List<Playlist> findByUserId(Long user_id);
    @Query("SELECT p FROM Playlist p JOIN p.songs s WHERE s.id= :song_id")
    List<Playlist> findAllPlaylistsContainingSong(@Param("song_id") Long song_id);


}

package com.Music.App.repository;

import com.Music.App.model.Playlist;
import com.Music.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PlaylistRepo extends JpaRepository<Playlist,Long> {
    List<Playlist> findByName(String name);
    List<Playlist> findByUserId(Long user_id);

}

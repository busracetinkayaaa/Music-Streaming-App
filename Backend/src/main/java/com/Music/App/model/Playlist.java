package com.Music.App.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Playlist extends BaseEntity{
private String name;

@ManyToOne
@JoinColumn(name="user_id")
private User user;

private int duration;

@ManyToMany()
@JoinTable(
        name = "playlist_songs",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id")
)
    List<Song> songs= new ArrayList<>();


}

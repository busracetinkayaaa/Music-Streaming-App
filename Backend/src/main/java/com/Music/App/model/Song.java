package com.Music.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


public class Song extends BaseEntity{
    private String title;
    private int duration;

    @ManyToMany()
    @JoinTable(name = "song_artists",joinColumns = @JoinColumn(name = "song_id"),inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> performers =new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists=new ArrayList<>();




}

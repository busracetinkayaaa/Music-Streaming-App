package com.Music.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Artist extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
    private String genre;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Album> albums=new ArrayList<>();

    @ManyToMany(mappedBy = "performers")
    @JsonIgnore
    private List<Song> songs= new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Song> ownedSongs = new ArrayList<>();


}


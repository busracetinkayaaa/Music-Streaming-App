package com.Music.App.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Album extends BaseEntity{
private String title;
private int releaseYear;
private int totalLengthOfAlbum;

@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
private List<Song> songs = new ArrayList<>();

@ManyToOne()
@JoinColumn(name="artist_id")
    private Artist artist;

}

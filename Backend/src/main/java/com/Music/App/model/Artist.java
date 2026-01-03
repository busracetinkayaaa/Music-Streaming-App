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
public class Artist extends BaseEntity{
    private String name;
    private String genre;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums=new ArrayList<>();

    @ManyToMany(mappedBy = "performers")
    private List<Song> songs= new ArrayList<>();



}


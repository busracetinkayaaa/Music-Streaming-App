package com.Music.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@SQLDelete(sql = "UPDATE song SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

public class Song extends BaseEntity{
    @NotBlank(message = "Şarkı ismi boş olamaz")
    @Size(min = 2, max = 100, message = "Şarkı ismi 2 ile 100 karakter arasında olmalıdır")
    private String title;

    @Min(value = 1, message = "Şarkı süresi en az 1 saniye olmalıdır")
    private int duration;

    private String imageUrl;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "song_performers",joinColumns = @JoinColumn(name = "song_id"),inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> performers =new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "main_artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists=new ArrayList<>();




}

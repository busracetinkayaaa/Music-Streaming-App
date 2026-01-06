package com.Music.App.dto;

import com.Music.App.model.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SongResponseDTO {
    private Long id;
    private String title;
    private int duration;
    private String artistName;
    private String imageUrl;


}

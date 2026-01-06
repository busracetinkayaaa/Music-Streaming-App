package com.Music.App.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaylistResponseDTO {
    private Long  id;
    private String name;

    private int totalDuration;
    private String formattedDuration;
    private List<SongResponseDTO> songs;
}

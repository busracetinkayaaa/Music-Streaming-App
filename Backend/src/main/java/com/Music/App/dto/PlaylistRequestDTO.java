package com.Music.App.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistRequestDTO {
    @NotBlank(message = "Playlist ismi boş olamaz.")
    private String name;
    private Long user_id;
}

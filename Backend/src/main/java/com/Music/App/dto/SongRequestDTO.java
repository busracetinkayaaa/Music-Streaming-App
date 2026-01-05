package com.Music.App.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class SongRequestDTO {
    @NotBlank(message = "Şarkı ismi boş olamaz")
    private String title;

    @Min(value = 1, message = "Süre 0'dan büyük olmalıdır")
    private int duration;

    private Long artist_id;
}

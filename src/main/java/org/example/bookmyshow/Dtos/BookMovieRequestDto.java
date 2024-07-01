package org.example.bookmyshow.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {

    private Long userId;
    private Long ShowId;
    private List<Long> showSeatId;
}

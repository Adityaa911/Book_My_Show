package org.example.bookmyshow.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshow.Models.Booking;


@Getter
@Setter
public class BookMovieResponseDto {

    private Booking booking;
    private ResponseStatus responseStatus;
}

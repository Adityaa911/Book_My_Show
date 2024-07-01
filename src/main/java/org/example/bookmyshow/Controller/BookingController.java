package org.example.bookmyshow.Controller;


import Exceptions.UserNotFoundException;
import org.example.bookmyshow.Dtos.BookMovieRequestDto;
import org.example.bookmyshow.Dtos.BookMovieResponseDto;
import org.example.bookmyshow.Service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

   private BookingService bookingService;

   BookingController(BookingService bookingService){
      this.bookingService = bookingService;
   }

   public BookMovieResponseDto BookMovie(BookMovieRequestDto RequestDto) throws UserNotFoundException {

      bookingService.BookMovie(
              RequestDto.getUserId(),
              RequestDto.getShowId(),
              RequestDto.getShowSeatId()
      );

              return null;
   }

}

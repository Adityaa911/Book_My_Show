package org.example.bookmyshow.Controller;


import org.example.bookmyshow.Dtos.ResponseStatus;
import org.example.bookmyshow.Exceptions.ShowNotFoundException;
import org.example.bookmyshow.Exceptions.UserNotFoundException;
import org.example.bookmyshow.Dtos.BookMovieRequestDto;
import org.example.bookmyshow.Dtos.BookMovieResponseDto;
import org.example.bookmyshow.Models.Booking;
import org.example.bookmyshow.Service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

   private BookingService bookingService;

   BookingController(BookingService bookingService){
      this.bookingService = bookingService;
   }

   public BookMovieResponseDto BookMovie(BookMovieRequestDto RequestDto) throws UserNotFoundException, ShowNotFoundException {

      BookMovieResponseDto responseDto =new BookMovieResponseDto();

      try {
         Booking booking=
         bookingService.BookMovie(
                 RequestDto.getUserId(),
                 RequestDto.getShowId(),
                 RequestDto.getShowSeatId()
         );

         responseDto.setBooking(booking);
         responseDto.setResponseStatus(ResponseStatus.SUCCESS);
      }
      catch (Exception e){
        responseDto.setResponseStatus(ResponseStatus.FAILED);
      }


              return responseDto;
   }

}

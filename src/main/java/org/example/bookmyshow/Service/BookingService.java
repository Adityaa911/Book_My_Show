package org.example.bookmyshow.Service;

import org.example.bookmyshow.Exceptions.ShowNotFoundException;
import org.example.bookmyshow.Exceptions.UserNotFoundException;
import org.example.bookmyshow.Models.*;
import org.example.bookmyshow.Repository.BookingRepository;
import org.example.bookmyshow.Repository.ShowRepository;
import org.example.bookmyshow.Repository.ShowSeatRepository;
import org.example.bookmyshow.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;
    private BookingRepository bookingRepository;

    BookingService(UserRepository userRepository,BookingRepository bookingRepository,ShowRepository showRepository,ShowSeatRepository showSeatRepository,PriceCalculator priceCalculator){
        this.userRepository=userRepository;
        this.showRepository =showRepository;
        this.showSeatRepository =showSeatRepository;
        this.priceCalculator= priceCalculator;
        this.bookingRepository=bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking BookMovie(Long userId, Long showId, List<Long> showSeatId) throws UserNotFoundException, ShowNotFoundException {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("user not found"+userId +"not found") ;
        }
       User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()){
            throw new ShowNotFoundException("this show-Id is worng" + showId);
        }

        Show show = optionalShow.get();

        List<ShowSeat> showSeats =showSeatRepository.findAllById(showSeatId);

        for (ShowSeat seat : showSeats){
            if (seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new RuntimeException("this show and seat is already booked " + seat.getId());
            }
        }


        Booking Savebooking = null;
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);

            showSeatRepository.save(showSeat);

        }
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setShowSeats(showSeats);
            booking.setBookingStatus(BookingStatus.PENDING);
            booking.setCreatedAt(new Date());
            booking.setAmount(priceCalculator.calculatePrice(showSeats,show));

            Savebooking = bookingRepository.save(booking);

        return booking;
    }
}

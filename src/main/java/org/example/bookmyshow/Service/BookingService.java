package org.example.bookmyshow.Service;

import Exceptions.ShowNotFoundException;
import Exceptions.UserNotFoundException;
import org.example.bookmyshow.Models.*;
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

    BookingService(UserRepository userRepository,ShowRepository showRepository,ShowSeatRepository showSeatRepository){
        this.userRepository=userRepository;
        this.showRepository =showRepository;
        this.showSeatRepository =showSeatRepository;
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

        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);

            showSeatRepository.save(showSeat);

        }
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setShowSeats(showSeats);
            booking.setBookingStatus(BookingStatus.PENDING);
            booking.setCreatedAt(new Date());
            booking.setAmount();


        return booking;
    }
}

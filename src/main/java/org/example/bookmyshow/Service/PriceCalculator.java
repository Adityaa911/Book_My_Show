package org.example.bookmyshow.Service;

import org.example.bookmyshow.Models.Show;
import org.example.bookmyshow.Models.ShowSeat;
import org.example.bookmyshow.Models.ShowSeatType;
import org.example.bookmyshow.Repository.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

   private ShowSeatTypeRepository showSeatTypeRepository;

   PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository){
       this.showSeatTypeRepository =showSeatTypeRepository;
   }
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
      List<ShowSeatType> showSeatTypes =
              showSeatTypeRepository.findAllByShow(show);

      int amount = 0;

      for(ShowSeat showSeat: showSeats){
          for (ShowSeatType showSeatType : showSeatTypes){
              if (showSeat.getSeat().getSeatType()
                      .equals(showSeatType.getSeatType())){
                  amount+= showSeatType.getPrice();
                  break;
              }
          }
      }
      return amount;
    }
}

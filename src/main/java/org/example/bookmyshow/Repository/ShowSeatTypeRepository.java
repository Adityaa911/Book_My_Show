package org.example.bookmyshow.Repository;

import org.example.bookmyshow.Models.Show;
import org.example.bookmyshow.Models.ShowSeat;
import org.example.bookmyshow.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {


    List<ShowSeatType> findAllByShow(Show show);
}

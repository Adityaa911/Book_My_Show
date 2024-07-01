package org.example.bookmyshow.Repository;

import org.example.bookmyshow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    ShowSeat save(ShowSeat showSeat);
}

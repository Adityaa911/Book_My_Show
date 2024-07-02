package org.example.bookmyshow.Repository;

import org.example.bookmyshow.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Override
    <S extends Booking> S save(S entity);
}

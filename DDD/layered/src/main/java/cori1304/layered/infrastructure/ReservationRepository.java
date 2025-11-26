package cori1304.layered.infrastructure;

import cori1304.layered.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByLodgingIdAndCheckInDateGreaterThanEqual(Long lodgingId, LocalDate date);
    List<Reservation> findByUserId(Long userId);
}

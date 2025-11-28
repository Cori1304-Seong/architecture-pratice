package cori1304.hexagonal.reservation.application.port.out;

import cori1304.hexagonal.reservation.domain.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoadReservationPort {
    Optional<Reservation> findById(Long id);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findOverlappingReservations(Long lodgingId, LocalDate checkIn, LocalDate checkOut);
}

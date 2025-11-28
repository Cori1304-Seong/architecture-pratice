package cori1304.hexagonal.reservation.application.port.in;

import cori1304.hexagonal.reservation.domain.Reservation;
import java.util.List;

public interface GetReservationUseCase {
    List<Reservation> getReservationsByUserId(Long userId);
    Reservation getReservationById(Long id);
}

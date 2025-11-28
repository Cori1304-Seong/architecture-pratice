package cori1304.hexagonal.reservation.application.port.in;

import cori1304.hexagonal.reservation.domain.Reservation;

public interface ManageReservationUseCase {
    Reservation confirmReservation(Long reservationId);
    Reservation cancelReservation(Long reservationId);
}

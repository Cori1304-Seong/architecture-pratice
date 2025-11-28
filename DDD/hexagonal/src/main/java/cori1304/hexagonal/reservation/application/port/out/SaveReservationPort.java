package cori1304.hexagonal.reservation.application.port.out;

import cori1304.hexagonal.reservation.domain.Reservation;

public interface SaveReservationPort {
    Reservation save(Reservation reservation);
}

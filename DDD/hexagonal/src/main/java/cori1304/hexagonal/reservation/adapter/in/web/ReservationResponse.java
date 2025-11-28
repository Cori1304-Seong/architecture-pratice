package cori1304.hexagonal.reservation.adapter.in.web;

import cori1304.hexagonal.reservation.domain.Reservation;
import cori1304.hexagonal.reservation.domain.ReservationStatus;
import lombok.Getter;

import java.time.LocalDate;

@Getter
class ReservationResponse {
    private final Long id;
    private final Long lodgingId;
    private final Long userId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int numberOfGuests;
    private final ReservationStatus status;

    ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.lodgingId = reservation.getLodgingId();
        this.userId = reservation.getUserId();
        this.checkInDate = reservation.getCheckInDate();
        this.checkOutDate = reservation.getCheckOutDate();
        this.numberOfGuests = reservation.getNumberOfGuests();
        this.status = reservation.getStatus();
    }
}
